#include<sys/resource.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<sys/stat.h>

#include<unistd.h>
#include<time.h>

#include<stdio.h>

/**
 * e.g.
 *  HOMEWORK:
 *      complete xpow(x,y)
 *  Sample Input:
 *      1 2
 *      3 3
 *      6 2
 *      0 0
 *
 *  Sample Output:
 *      1
 *      27
 *      36
 **/
int Main() {
    int x, y;
    while(scanf("%d %d", &x, &y)) {
        if (x == 0 && y == 0)
            break;
        printf("%d\n", xpow(x, y));
    }
    return 0;
}

int xpow(int x, int y) {
    int z = x;
    while(y--) {
        z *= x;
    }
    return z;
}




#define _MAIN_ main
int _MAIN_(int argc, char* argv[]) {
    pid_t pid;
    if ((pid = fork()) < 0) 
        return -1;
    if (pid == 0) {
        freopen("stdin.in", "r", stdin);
        freopen("usage.usg", "w", stdout);
        fprintf(stdout, "testout_begin\n");
        Main();
        return 0;
    }

    time_t t_beg, t_end;
    struct stat s_usg;
    struct rusage ru;
    
    t_beg = time(NULL);
    wait4(pid, NULL, WUNTRACED, &ru);
    kill(pid, SIGTERM);
    stat("usage.usg", &s_usg);
    t_end = time(NULL);

    FILE *fp = fopen("usage.usg", "a");
    fprintf(fp, "testout_end\n");
    fprintf(fp, "%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld"
            "%ld,%ld,%ld,%ld,%ld,%ld",
            ru.ru_utime.tv_sec,ru.ru_utime.tv_usec,ru.ru_stime.tv_sec,
            ru.ru_stime.tv_usec,ru.ru_maxrss,ru.ru_ixrss,ru.ru_idrss,
            ru.ru_isrss,ru.ru_minflt,ru.ru_majflt,ru.ru_nswap,
            ru.ru_inblock,ru.ru_oublock,ru.ru_msgsnd,ru.ru_msgrcv,
            ru.ru_nsignals,ru.ru_nvcsw,ru.ru_nivcsw);
    fclose(fp);

    const char* RSAPUB = "\
-----BEGIN PUBLIC KEY-----\n\
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxZgAr9aa+3UK2PI2x0Kk\n\
7iVTRBTZ73M+zA1F130zqzUwgT5Nsu0O1ZwO2M4MxtoE/6j1EAf9VRKx/UOavVxe\n\
50NbERaijTGryiS7bMjRwdvKCoR7aOARqwf1NVYM4Pn75LWwqzzYFVpq4GhlCYMT\n\
UomGbW+Wr2Rsh6V8ZoX6Off6dTB7ZZFck7PqwpORzptawkSvqlVv+8+PdI2yjovn\n\
8V/oxFKvFpBbh06aER16L6oGdX+sAg0Aqz/SEIocNL/05n6lE0k3y5iV8+bDbAHd\n\
UI5CWfzXCpKut6ctZsPM5CGaKlUKAOiUjTYY+OnB0kSBArLj51Zcn0SdQdWIMuWl\n\
GQIDAQAB\n\
-----END PUBLIC KEY-----";
    FILE *fp_rsapub = fopen("rsapub.key", "w");
    fprintf(fp_rsapub, RSAPUB);
    fclose(fp_rsapub);

    execlp("openssl", "rsautl","-encrypt","-in","usage.usg","-inkey",
            "rsapub.key","-pubin","-out","usage.rpt", NULL);
    return 0;
}
