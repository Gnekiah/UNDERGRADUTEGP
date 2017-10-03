#include<sys/ptrace.h>
#include<sys/user.h>

#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

#ifdef __i386
#include"../callrule_32.h"
#else
#include"../callrule_64.h"
#endif




int main(int argc, char* argv[]) {
    pid_t child;

    if (argc == 1) {
        exit(0);
    }

    char* pgm = argv[1];
    child = fork();
    if (child == 0) {
        freopen("stdin.in", "r", stdin);
        freopen("stdout.out", "w", stdout);
        ptrace(PTRACE_TRACEME, 0, NULL, NULL);
        execv(pgm, NULL);
    }
    else {
        int status;
        while(waitpid(child, &status, 0) && !WIFEXITED(status)) {
            struct user_regs_struct regs;
            ptrace(PTRACE_GETREGS, child, NULL, &regs);
            fprintf(stderr, "pid= %d, call id= %ld, text= %s \n", child, regs.CALLID, CALL_DESC[regs.CALLID]);
            ptrace(PTRACE_SYSCALL, child, NULL, NULL);
        }
    }
    exit(0);
}
