#include<sys/ptrace.h>
#include<sys/user.h>
#include<sys/resource.h>
#include<sys/wait.h>
#include<sys/time.h>
#include<sys/types.h>

#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<errno.h>
#include<signal.h>

#ifdef __i386
#include"callrule_32.h"
#else
#include"callrule_64.h"
#endif

#include"monitor.h"



/**
 * flg means:
 * 0 - do not trace
 * 1 - trace on loosen
 * 2 - trace on normally
 * 3 - trace on strictly
 * else, return OPT_ERROR
 */
int parse_traceflg(char* argv, int* flg) {
    int i = 0;
    if (isdigit(argv[0]))
        (*flg) = argv[0] - 48;
    if (argv[1] != '\0' || (*flg) < 0 || (*flg) > 3)
        return OPT_ERROR;
    return OPT_RIGHT;
}


/**
 * return
 *  OPT_ERROR if argv not a integer 
 *  OPT_NULL if argv is 0
 *  OPT_RIGHT if argv bigger than 0
 */
char parse_opt(char* argv, struct rlimit *r) {
    int i = 0;
    rlim_t value = 0;
    while(argv[i]) {
        if (isdigit(argv[i]))
            value = value * 10 + argv[i++] - 48;
        else 
            return OPT_ERROR;
    }
    if (value == 0)
        return OPT_NULL;
    r->rlim_cur = value;
    r->rlim_max = value;
    return OPT_RIGHT;
}


/* write resource usage info to file */ 
void write_rusage(struct rusage *ru) {
    FILE *fp = fopen(p_usg, "w");
    fprintf(fp, "utime_sec=%ld\nutime_usec=%ld\n"
            "stime_sec=%ld\nstime_usec=%ld\n"
            "maxrss=%ld\nixrss=%ld\nidrss=%ld\n"
            "isrss=%ld\nminflt=%ld\nmajflt=%ld\n"
            "nswap=%ld\ninblock=%ld\noublock=%ld\n"
            "msgsnd=%ld\nmsgrcv=%ld\nnsignals=%ld\n"
            "nvcsw=%ld\nnivcsw=%ld\n",
            ru->ru_utime.tv_sec, ru->ru_utime.tv_usec,
            ru->ru_stime.tv_sec, ru->ru_stime.tv_usec,
            ru->ru_maxrss, ru->ru_ixrss, ru->ru_idrss,
            ru->ru_isrss, ru->ru_minflt, ru->ru_majflt,
            ru->ru_nswap, ru->ru_inblock, ru->ru_oublock,
            ru->ru_msgsnd, ru->ru_msgrcv, ru->ru_nsignals,
            ru->ru_nvcsw, ru->ru_nivcsw);
    fclose(fp);
}


/* write call counts to file */
void write_callcnt() {
    FILE *fp = fopen(p_callcnt, "w");
    int i;
    for (i=0; i < CALL_AMOUNT; i++) {
        if (callcnt[i])
            fprintf(fp, "%d: %d  %s\n", i, callcnt[i], CALL_DESC[i]);
    }
    fclose(fp);
}


/**
 * len(argv) = 13
 * argv[1] is program path
 * argv[2] - argv[9] are resource limited options
 * argv[10] is stdin.in path
 * argv[11] is stdout.out path
 * argv[12] point out trace or not
 *      0 - do not trace
 *      1 - trace on loosen
 *      2 - trace on normally
 *      3 - trace on strictly
 */
int main(int argc, char* argv[]) {

    /* initialization */
    char* pgm;
    /* flg is argv[12], point out class of trace */
    int i, flg=0;
    struct rlimit r_lims[8];
    pid_t pid, mpid;
    if (argc != 13) 
        exit(VALUE_LACK);

    pgm = argv[1];
    char opt_rst=OPT_NULL, opt_flg[8];
    for(i=0; i < 8; i++) {
        opt_flg[i] = parse_opt(argv[i+2], &r_lims[i]);
        opt_rst |= opt_flg[i];
    }
    if ((opt_rst & OPT_ERROR) == OPT_ERROR || parse_traceflg(argv[12],&flg) == OPT_ERROR)
        exit(VALUE_ERROR);
    if ((pid = fork()) < 0) 
        exit(PID_CREATED_FAILED);

    /* run child process */
    if (pid == 0) {
        /* lim_err is error code of setrlimit */
        int lim_err = 0;
        freopen(argv[10], "r", stdin);
        freopen(argv[11], "w", stdout);

        /* set resources limit */
        for(i=0; i < 8; i++) {
            if (opt_flg[i] == OPT_RIGHT && setrlimit(resources[i], &r_lims[i])) {
                lim_err = errno;
                return lim_err;
            }
        }

        /* while flg = 0, do not trace the child process */
        if (flg != 0)
            ptrace(PTRACE_TRACEME, 0, NULL, NULL);
        execv(pgm, NULL);
        exit(PID_EXEC_ERROR);
    }

    /* run monitor process to gather pid resource usage */
    if ((mpid = fork()) < 0)
        exit(PID_CREATED_FAILED);
    if (mpid == 0) {
        wait4(pid, &status, WUNTRACED, &usage);
        write_rusage(&usage);
        exit(SUCCESS);
    }

    /* trace the process */
    int status;
    int clid;  /* call id */
    struct user_regs_struct regs;
    struct rusage usage;
    /* if need not trace child process */
    if (flg == 0) {
        wait4(pid, &status, WUNTRACED, &usage);
        write_rusage(&usage);
        exit(SUCCESS);
    }
    /* trace system calls */
    while(waitpid(pid, &status, 0) && !WIFEXITED(status)) {
        ptrace(PTRACE_GETREGS, pid, NULL, &regs);
        clid = regs.CALLID;
        callcnt[clid]++;
        /*******************************************/
        //fprintf(stderr, "%d=%s\n", clid, CALL_DESC[clid]);
        wait4(pid, &status, WNOHANG, &usage);
        /*******************************************/

        /* return while child process call sys_call without authority */
        if (CALL_AUTH[clid] < flg) {
            wait4(pid, &status, WNOHANG, &usage);
            kill(pid, SIGTERM);
            write_rusage(&usage);
            write_callcnt();
            exit(SYSCALL_NOAUTH);
        }

        ptrace(PTRACE_SYSCALL, pid, NULL, NULL);
    }
    wait4(pid, &status, WNOHANG, &usage);
    kill(pid, SIGTERM);
    write_rusage(&usage);
    write_callcnt();
    exit(SUCCESS);
}
