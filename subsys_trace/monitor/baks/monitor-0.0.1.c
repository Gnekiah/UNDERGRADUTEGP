#include<sys/ptrace.h>
#include<sys/user.h>
#include<sys/resource.h>

#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#ifdef __i386
#include"callrule_32.h"
#else
#include"callrule_64.h"
#endif

#include"monitor.h"


/* return value if argv is digit else -1 */
rlim_t parse_digit(char* argv) {
    int i = 0;
    rlim_t value = 0;
    while(argv[i]) {
        if (isdigit(argv[i]))
            value = value * 10 + argv[i++] - 48;
        else
            return -1;
    }
    return value;
}



/* I'm tired now, 2017-04-05 02:15:00 */
int parse_opt(int argc, char* argv[], char** pgm, struct rlimit *ru, struct rlimit *rm) {
    const char* ru_opt = "-u";
    const char* rm_opt = "-m";
    rlim_t value = 0;
    switch(argc) {
        case 1:
            return VALUE_MISSED;
        case 2:
            if (!strcmp(argv[1], "-h")) {
                fprintf(stderr, "Usage: %s <exe> [<ru> <val>] [<rm> <val>]\n", argv[0]);
                fprintf(stderr, "e.g.   %s test.exe -u 128 -m 64\n", argv[0]);
                fprintf(stderr, "       %s test.exe -m 32\n", argv[0]);
                fprintf(stderr, "       %s -u 128 -m 64 test.exe\n", argv[0]);
                return PRINT_USAGE_INFO;
            }
            pgm = argv[1];
            return SUCCESS;
        case 4:
            if (!strcmp(argv[1], ru_opt)) {
                value = parse_digit(argv[2]);
                if (value == -1) 
                    return OPTION_ERROR;
                ru->rlim_cur = value;
                ru->rlim_max = value;
                pgm = argv[3];
                return SUCCESS;
            }
            if (!strcmp(argv[2], ru_opt)) {
                value = parse_digit(argv[3]);
                if (value == -1) 
                    return OPTION_ERROR;
                ru->rlim_cur = value;
                ru->rlim_max = value;
                pgm = argv[1];
                return SUCCESS;
            }
            if (!strcmp(argv[1], rm_opt)) {
                value = parse_digit(argv[2]);
                if (value == -1)
                    return OPTION_ERROR;
                rm->rlim_cur = value;
                rm->rlim_max = value;
                pgm = argv[3];
                return SUCCESS; 
            }
            if (!strcmp(argv[2], rm_opt)) {
                value = parse_digit(argv[3]);
                if (value == -1) 
                    return OPTION_ERROR;
                rm->rlim_cur = value;
                rm->rlim_max = value;
                pgm = argv[1];
                return SUCCESS;
            }
            return OPTION_ERROR;
        case 6:
            if (!strcmp(argv[1], ru_opt) || !strcmp(argv[3], rm_opt)) {
                value = parse_digit(argv[2]);
                if (value == -1)
                    return OPTION_ERROR;
                ru->rlim_cur = value;
                ru->rlim_max = value;
                value = parse_digit(argv[4]);
                if (value == -1)
                    return OPTION_ERROR;
                rm->rlim_cur = value;
                rm->rlim_max = value;
                pgm = argv[5];
                return SUCCESS;
            }
            if (!strcmp(argv[1], rm_opt) || !strcmp(argv[3], ru_opt)) {
                value = parse_digit(argv[2]);
                if (value == -1)
                    return OPTION_ERROR;
                rm->rlim_cur = value;
                rm->rlim_max = value;
                value = parse_digit(argv[4]);
                if (value == -1)
                    return OPTION_ERROR;
                ru->rlim_cur = value;
                ru->rlim_max = value;
                pgm = argv[5];
                return SUCCESS;
            }
            if (!strcmp(argv[2], ru_opt) || !strcmp(argv[4], rm_opt)) {
                value = parse_digit(argv[3]);
                if (value == -1)
                    return OPTION_ERROR;
                ru->rlim_cur = value;
                ru->rlim_max = value;
                value = parse_digit(argv[5]);
                if (value == -1)
                    return OPTION_ERROR;
                rm->rlim_cur = value;
                rm->rlim_max = value;
                pgm = argv[1];
                return SUCCESS;
            }
            if (!strcmp(argv[2], rm_opt) || !strcmp(argv[4], ru_opt)) {
                value = parse_digit(argv[3]);
                if (value == -1)
                    return OPTION_ERROR;
                rm->rlim_cur = value;
                rm->rlim_max = value;
                value = parse_digit(argv[5]);
                if (value == -1)
                    return OPTION_ERROR;
                ru->rlim_cur = value;
                ru->rlim_max = value;
                pgm = argv[1];
                return SUCCESS;
            }
            return OPTION_ERROR;
        case 3:
        case 5:
        default:
            return VALUE_ERROR;
    }
}


int main(int argc, char* argv[]) {
    char pgm[128];
    struct rlimit ru, rm;
    pid_t pid;
    
    int opt = parse_opt(argc, argv, &pgm, &ru, &rm);
    if (opt) {
        exit(FAILURE);
    }
    pid = fork();
    if (pid == 0) {
        freopen("stdin.in", "r", stdin);
        freopen("stdout.out", "w", stdout);
        ptrace(PTRACE_TRACEME, 0, NULL, NULL);
        execv(pgm, NULL);
    }
    else {
        int status;
        while(waitpid(pid, &status, 0) && !WIFEXITED(status)) {
            struct user_regs_struct regs;
            ptrace(PTRACE_GETREGS, pid, NULL, &regs);
            fprintf(stderr, "pid= %d, call id= %ld, text= %s \n", pid, regs.CALLID, CALL_DESC[regs.CALLID]);
            ptrace(PTRACE_SYSCALL, pid, NULL, NULL);
        }
    }
    exit(FAILURE);
}
