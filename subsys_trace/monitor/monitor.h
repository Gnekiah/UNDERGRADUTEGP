#ifndef _MONITOR_H
#define _MONITOR_H

/* return's values of program */
#define SUCCESS                 0
#define RLIM_EPERM              EPERM       // 1
#define RLIM_EFAULT             EFAULT      // 14
#define RLIM_EINVAL             EINVAL      // 22
#define VALUE_LACK              201
#define VALUE_ERROR             202
#define PID_CREATED_FAILED      203
#define PTHREAD_CREATED_FAILED  204
#define PID_EXEC_ERROR          205
#define SYSCALL_NOAUTH          206 

/* return's values of function parse_opt */
#define OPT_NULL            000000000
#define OPT_ERROR           000000001
#define OPT_RIGHT           000000010

/* values of RLIMIT */
#define R_AS                0
#define R_CORE              1
#define R_CPU               2
#define R_DATA              3
#define R_FSIZE             4
#define R_NOFILE            5
#define R_RSS               6
#define R_STACK             7

/* resources */
static const int resources[] = {
    RLIMIT_AS,
    RLIMIT_CORE,
    RLIMIT_CPU,
    RLIMIT_DATA,
    RLIMIT_FSIZE,
    RLIMIT_NOFILE,
    RLIMIT_RSS,
    RLIMIT_STACK };

/* file name to save rusage info */
static const char* p_usg = "usage.usg";

/* file name to save call count info */
static const char* p_callcnt = "calls.cnt";

#endif
