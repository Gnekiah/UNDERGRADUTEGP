#include<stdio.h>
#include<stdint.h>
#include<sys/resource.h>

void print_rlimit(struct rlimit *r, const char *name) {
    int64_t cur;
    int64_t max;
    cur = r->rlim_cur;
    max = r->rlim_max;
    printf("RLIMIT_%s: rlim_cur => %#llx, rlim_max => %#llx\n", name, cur, max);
}

int main(int argc, char* argv[]) {
    struct rlimit rlim;
    int resources[] = {RLIMIT_CORE, RLIMIT_CPU, RLIMIT_DATA, RLIMIT_FSIZE,
    RLIMIT_MEMLOCK, RLIMIT_NOFILE, RLIMIT_NPROC, RLIMIT_RSS, RLIMIT_STACK};
    const char* name[] = {"core", "cpu", "data", "fsize", "memlock",
    "nofile", "nproc", "rss", "stack"};
    int i = 0;
    for (;i < 9; i++) {
        getrlimit(resources[i], &rlim);
        print_rlimit(&rlim, name[i]);
    }
    return 0;
}
