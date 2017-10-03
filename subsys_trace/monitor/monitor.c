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
    FILE *fp = fopen(p_usg, "a");
    fprintf(fp, "testout_end\n");
    fprintf(fp, "%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld"
            "%ld,%ld,%ld,%ld,%ld,%ld",
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
 * argv[11] is stdout.out path, HERE WE CHANGE THE VALUE TO THE FILE usage.usg
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
    pid_t pid;
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
        freopen(p_usg, "w", stdout);
        fprintf(stdout, "testout_begin\n");

        /* set resources limit */
        for(i=0; i < 8; i++) {
            if (opt_flg[i] == OPT_RIGHT && setrlimit(resources[i], &r_lims[i])) {
                lim_err = errno;
                return lim_err;
            }
        }

        ptrace(PTRACE_TRACEME, 0, NULL, NULL);
        execv(pgm, NULL);
        exit(PID_EXEC_ERROR);
    }

    /* trace the process */
    int status;
    int clid;  /* call id */
    int exit_status = SUCCESS;
    struct user_regs_struct regs;
    struct rusage usage;

    /* trace system calls */
    while(waitpid(pid, &status, 0) && !WIFEXITED(status)) {
        ptrace(PTRACE_GETREGS, pid, NULL, &regs);
        clid = regs.CALLID;
        callcnt[clid]++;

        /* return while child process call sys_call without authority */
        if (CALL_AUTH[clid] < flg) {
            exit_status = SYSCALL_NOAUTH;
            break;
        }
        ptrace(PTRACE_SYSCALL, pid, NULL, NULL);
    }
    
    getrusage(RUSAGE_CHILDREN, &usage);
    write_rusage(&usage);
    write_callcnt();
    kill(pid, SIGTERM);
    exit(exit_status);
}
