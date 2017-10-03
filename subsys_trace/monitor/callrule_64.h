#ifndef _SYSCALL_RULE_64_H
#define _SYSCALL_RULE_64_H


#define CALLID  orig_rax
#define RETURN  rax
#define ARG1    rdi
#define ARG2    rsi
#define ARG3    rdx
#define ARG4    r10
#define ARG5    r8
#define ARG6    r9

static const int CALL_AMOUNT = 395;

/* count for call times */
static int callcnt[395] = {0};


static const char CALL_AUTH[] = {
    3,      /* 0, restart_syscall */
    3,      /* 1, exit */
    3,      /* 2, fork */
    3,      /* 3, read */
    2,      /* 4, write */
    3,      /* 5, open */
    2,      /* 6, close */
    0,      /* 7, waitpid */
    0,      /* 8, creat */
    3,      /* 9, link */
    3,      /* 10, unlink */
    3,      /* 11, execve */
    3,      /* 12, chdir */
    1,      /* 13, time */
    2,      /* 14, mknod */
    1,      /* 15, chmod */
    1,      /* 16, lchown */
    0,      /* 17, sys_break */
    2,      /* 18, oldstat */
    2,      /* 19, lseek */
    1,      /* 20, getpid */
    3,      /* 21, mount */
    0,      /* 22, umount */
    0,      /* 23, setuid */
    1,      /* 24, getuid */
    0,      /* 25, stime */
    0,      /* 26, ptrace */
    0,      /* 27, alarm */
    2,      /* 28, oldfstat */
    1,      /* 29, pause */
    1,      /* 30, utime */
    0,      /* 31, sys_stty */
    1,      /* 32, sys_gtty */
    2,      /* 33, access */
    0,      /* 34, nice */
    1,      /* 35, sys_ftime */
    0,      /* 36, sync */
    0,      /* 37, kill */
    2,      /* 38, rename */
    2,      /* 39, mkdir */
    2,      /* 40, rmdir */
    2,      /* 41, dup */
    0,      /* 42, pipe */
    1,      /* 43, times */
    0,      /* 44, sys_prof */
    0,      /* 45, brk */
    0,      /* 46, setgid */
    1,      /* 47, getgid */
    0,      /* 48, signal */
    1,      /* 49, geteuid */
    1,      /* 50, getegid */
    0,      /* 51, acct */
    0,      /* 52, umount2 */
    0,      /* 53, sys_lock */
    0,      /* 54, ioctl */
    2,      /* 55, fcntl */
    0,      /* 56, sys_mpx */
    0,      /* 57, setpgid */
    0,      /* 58, sys_ulimit */
    3,      /* 59, sys_olduname */
    1,      /* 60, umask */
    0,      /* 61, chroot */
    1,      /* 62, ustat */
    2,      /* 63, dup2 */
    1,      /* 64, getppid */
    1,      /* 65, getpgrp */
    0,      /* 66, setsid */
    0,      /* 67, sigaction */
    1,      /* 68, sgetmask */
    0,      /* 69, ssetmask */
    0,      /* 70, setreuid */
    0,      /* 71, setregid */
    0,      /* 72, sigsuspend */
    0,      /* 73, sigpending */
    0,      /* 74, sethostname */
    0,      /* 75, setrlimit */
    1,      /* 76, getrlimit */
    1,      /* 77, getrusage */
    1,      /* 78, gettimeofday */
    0,      /* 79, settimeofday */
    1,      /* 80, getgroups */
    0,      /* 81, setgroups */
    0,      /* 82, sys_select */
    2,      /* 83, symlink */
    1,      /* 84, oldlstat */
    2,      /* 85, readlink */
    0,      /* 86, uselib */
    0,      /* 87, swapon */
    0,      /* 88, reboot */
    2,      /* 89, readdir */
    0,      /* 90, mmap */
    0,      /* 91, munmap */
    2,      /* 92, truncate */
    2,      /* 93, ftruncate */
    1,      /* 94, fchmod */
    1,      /* 95, fchown */
    1,      /* 96, getpriority */
    0,      /* 97, setpriority */
    0,      /* 98, sys_profil */
    1,      /* 99, statfs */
    1,      /* 100, fstatfs */
    0,      /* 101, sys_ioperm */
    2,      /* 102, socketcall */
    0,      /* 103, syslog */
    0,      /* 104, setitimer */
    1,      /* 105, getitimer */
    2,      /* 106, stat */
    2,      /* 107, lstat */
    2,      /* 108, fstat */
    1,      /* 109, olduname */
    0,      /* 110, sys_iopl */
    0,      /* 111, vhangup */
    0,      /* 112, sys_idle */
    0,      /* 113, sys_vm86old */
    0,      /* 114, wait4 */
    0,      /* 115, swapoff */
    1,      /* 116, sysinfo */
    0,      /* 117, ipc */
    2,      /* 118, fsync */
    0,      /* 119, sigreturn */
    0,      /* 120, clone */
    0,      /* 121, setdomainname */
    1,      /* 122, uname */
    0,      /* 123, cacheflush */
    0,      /* 124, adjtimex */
    0,      /* 125, mprotect */
    0,      /* 126, sigprocmask */
    0,      /* 127, sys_create_module */
    0,      /* 128, init_module */
    0,      /* 129, delete_module */
    0,      /* 130, sys_get_kernel_syms */
    0,      /* 131, quotactl */
    1,      /* 132, getpgid */
    2,      /* 133, fchdir */
    0,      /* 134, bdflush */
    1,      /* 135, sysfs */
    0,      /* 136, personality */
    0,      /* 137, sys_afs_syscall */
    0,      /* 138, setfsuid */
    0,      /* 139, setfsgid */
    2,      /* 140, _llseek */
    2,      /* 141, getdents */
    2,      /* 142, _newselect */
    2,      /* 143, flock */
    0,      /* 144, msync */
    2,      /* 145, readv */
    2,      /* 146, writev */
    1,      /* 147, getsid */
    0,      /* 148, fdatasync */
    0,      /* 149, _sysctl */
    0,      /* 150, mlock */
    0,      /* 151, munlock */
    0,      /* 152, mlockall */
    0,      /* 153, munlockall */
    0,      /* 154, sched_setparam */
    0,      /* 155, sched_getparam */
    0,      /* 156, sched_setscheduler */
    0,      /* 157, sched_getscheduler */
    3,      /* 158, sched_yield */
    0,      /* 159, sched_get_priority_max */
    0,      /* 160, sched_get_priority_min */
    0,      /* 161, sched_rr_get_interval */
    1,      /* 162, nanosleep */
    0,      /* 163, mremap */
    0,      /* 164, setresuid */
    1,      /* 165, getresuid */
    0,      /* 166, sys_vm86 */
    0,      /* 167, sys_query_module */
    2,      /* 168, poll */
    0,      /* 169, nfsservctl */
    0,      /* 170, setresgid */
    1,      /* 171, getresgid */
    0,      /* 172, prctl */
    0,      /* 173, rt_sigreturn */
    0,      /* 174, rt_sigaction */
    0,      /* 175, rt_sigprocmask */
    0,      /* 176, rt_sigpending */
    0,      /* 177, rt_sigtimedwait */
    0,      /* 178, rt_sigqueueinfo */
    0,      /* 179, rt_sigsuspend */
    2,      /* 180, pread64 */
    2,      /* 181, pwrite64 */
    1,      /* 182, chown */
    1,      /* 183, getcwd */
    1,      /* 184, capget */
    0,      /* 185, capset */
    0,      /* 186, sigaltstack */
    2,      /* 187, sendfile */
    0,      /* 188, getpmsg */
    0,      /* 189, putpmsg */
    0,      /* 190, vfork */
    1,      /* 191, ugetrlimit */
    0,      /* 192, mmap2 */
    2,      /* 193, truncate64 */
    2,      /* 194, ftruncate64 */
    2,      /* 195, stat64 */
    2,      /* 196, lstat64 */
    2,      /* 197, fstat64 */
    1,      /* 198, lchown32 */
    1,      /* 199, getuid32 */
    1,      /* 200, getgid32 */
    1,      /* 201, geteuid32 */
    1,      /* 202, getegid32 */
    0,      /* 203, setreuid32 */
    0,      /* 204, setregid32 */
    1,      /* 205, getgroups32 */
    0,      /* 206, setgroups32 */
    1,      /* 207, fchown32 */
    0,      /* 208, setresuid32 */
    1,      /* 209, getresuid32 */
    0,      /* 210, setresgid32 */
    1,      /* 211, getresgid32 */
    1,      /* 212, chown32 */
    0,      /* 213, setuid32 */
    0,      /* 214, setgid32 */
    0,      /* 215, setfsuid32 */
    0,      /* 216, setfsgid32 */
    0,      /* 217, pivot_root */
    0,      /* 218, mincore */
    0,      /* 219, madvise */
    2,      /* 220, socket */
    2,      /* 221, bind */
    2,      /* 222, connect */
    2,      /* 223, listen */
    2,      /* 224, accept */
    2,      /* 225, getsockname */
    2,      /* 226, getpeername */
    2,      /* 227, socketpair */
    2,      /* 228, send */
    2,      /* 229, sendto */
    2,      /* 230, recv */
    3,      /* 231, recvfrom */
    0,      /* 232, shutdown */
    2,      /* 233, setsockopt */
    2,      /* 234, getsockopt */
    2,      /* 235, sendmsg */
    2,      /* 236, recvmsg */
    1,      /* 237, semop */
    1,      /* 238, semget */
    1,      /* 239, semctl */
    0,      /* 240, msgsnd */
    0,      /* 241, msgrcv */
    0,      /* 242, msgget */
    0,      /* 243, msgctl */
    0,      /* 244, shmat */
    0,      /* 245, shmdt */
    0,      /* 246, shmget */
    0,      /* 247, shmctl */
    2,      /* 248, getdents64 */
    2,      /* 249, fcntl64 */
    0,      /* 250, tux */
    0,      /* 251 is unused */
    1,      /* 252, gettid */
    0,      /* 253, readahead */
    0,      /* 254, setxattr */
    0,      /* 255, lsetxattr */
    0,      /* 256, fsetxattr */
    0,      /* 257, getxattr */
    0,      /* 258, lgetxattr */
    0,      /* 259, fgetxattr */
    0,      /* 260, listxattr */
    0,      /* 261, llistxattr */
    0,      /* 262, flistxattr */
    0,      /* 263, removexattr */
    0,      /* 264, lremovexattr */
    0,      /* 265, fremovexattr */
    0,      /* 266, tkill */
    2,      /* 267, sendfile64 */
    0,      /* 268, futex */
    0,      /* 269, sched_setaffinity */
    0,      /* 270, sched_getaffinity */
    0,      /* 271, set_thread_area */
    0,      /* 272, get_thread_area */
    0,      /* 273, io_setup */
    0,      /* 274, io_destroy */
    0,      /* 275, io_getevents */
    0,      /* 276, io_submit */
    0,      /* 277, io_cancel */
    0,      /* 278, fadvise64 */
    0,      /* 279 is unused */
    0,      /* 280, exit_group */
    0,      /* 281, lookup_dcookie */
    0,      /* 282, epoll_create */
    0,      /* 283, epoll_ctl */
    0,      /* 284, epoll_wait */
    0,      /* 285, remap_file_pages */
    0,      /* 286, set_tid_address */
    0,      /* 287, timer_create */
    0,      /* 288, timer_settime */
    0,      /* 289, timer_gettime */
    0,      /* 290, timer_getoverrun */
    0,      /* 291, timer_delete */
    0,      /* 292, clock_settime */
    0,      /* 293, clock_gettime */
    0,      /* 294, clock_getres */
    0,      /* 295, clock_nanosleep */
    2,      /* 296, statfs64 */
    2,      /* 297, fstatfs64 */
    0,      /* 298, tgkill */
    1,      /* 299, utimes */
    0,      /* 300, fadvise64_64 */ 
    0,      /* 301, vserver */
    1,      /* 302, mbind */
    0,      /* 303, get_mempolicy */
    0,      /* 304, set_mempolicy */
    0,      /* 305, mq_open */
    0,      /* 306, mq_unlink */
    0,      /* 307, mq_timedsend */
    0,      /* 308, mq_timedreceive */
    0,      /* 309, mq_notify */
    0,      /* 310, mq_getsetattr */
    0,      /* 311, kexec */
    0,      /* 312, waitid */
    0,      /* 313, add_key */
    0,      /* 314, request_key */
    0,      /* 315, keyctl */
    0,      /* 316, ioprio_set */
    0,      /* 317, ioprio_get */
    0,      /* 318, inotify_init */
    0,      /* 319, inotify_add_watch */
    0,      /* 320, inotify_rm_watch */
    0,      /* 321 is unused */
    0,      /* 322, migrate_pages */
    1,      /* 323, openat */
    1,      /* 324, mkdirat */
    1,      /* 325, mknodat */
    1,      /* 326, fchownat */
    1,      /* 327, futimesat */
    1,      /* 328, fstatat64 */
    1,      /* 329, unlinkat */
    1,      /* 330, renameat */
    1,      /* 331, linkat */
    1,      /* 332, symlinkat */
    1,      /* 333, readlinkat */
    1,      /* 334, fchmodat */
    1,      /* 335, faccessat */
    0,      /* 336, pselect6 */
    0,      /* 337, ppoll */
    0,      /* 338, unshare */
    0,      /* 339, set_robust_list */
    0,      /* 340, get_robust_list */
    0,      /* 341, splice */
    0,      /* 342, sync_file_range */
    0,      /* 343, tee */
    0,      /* 344, vmsplice */
    0,      /* 345, move_pages */
    0,      /* 346, getcpu */
    0,      /* 347, epoll_pwait */
    0,      /* 348, utimensat */
    0,      /* 349, signalfd */
    0,      /* 350, timerfd_create */
    0,      /* 351, eventfd */
    0,      /* 352, fallocate */
    0,      /* 353, timerfd_settime */
    0,      /* 354, timerfd_gettime */
    0,      /* 355, signalfd4 */
    0,      /* 356, eventfd2 */
    0,      /* 357, epoll_create1 */
    2,      /* 358, dup3 */
    0,      /* 359, pipe2 */
    0,      /* 360, inotify_init1 */
    0,      /* 361, preadv */
    0,      /* 362, pwritev */
    0,      /* 363, rt_tgsigqueueinfo */
    0,      /* 364, perf_event_open */
    0,      /* 365, recvmmsg */
    0,      /* 366, accept4 */
    0,      /* 367, fanotify_init */
    0,      /* 368, fanotify_mark */
    0,      /* 369, prlimit64 */
    0,      /* 370, name_to_handle_at */
    0,      /* 371, open_by_handle_at */
    0,      /* 372, clock_adjtime */
    0,      /* 373, syncfs */
    0,      /* 374, sendmmsg */
    0,      /* 375, setns */
    0,      /* 376, process_vm_readv */
    0,      /* 377, process_vm_writev */
    0,      /* 378, kcmp */
    0,      /* 379, finit_module */
    0,      /* 380, sched_getattr */
    0,      /* 381, sched_setattr */
    1,      /* 382, renameat2 */
    0,      /* 383, seccomp */
    2,      /* 384, getrandom */
    0,      /* 385, memfd_create */
    0,      /* 386, bpf */
    0,      /* 387, execveat */
    0,      /* 388, userfaultfd */
    0,      /* 389, membarrier */
    0,      /* 390, mlock2 */
    0,      /* 391, copy_file_range */
    0,      /* 392, preadv2 */
    0,      /* 393, pwritev2 */
    0,      /* 394, syscalls */
};


static const char* CALL_DESC[] = {
    "restart_syscall",
    "exit",
    "fork",
    "read",
    "write",
    "open",
    "close",
    "waitpid",
    "creat",
    "link",
    "unlink",
    "execve",
    "chdir",
    "time",
    "mknod",
    "chmod",
    "lchown",
    "sys_break",
    "oldstat",
    "lseek",
    "getpid",
    "mount",
    "umount",
    "setuid",
    "getuid",
    "stime",
    "ptrace",
    "alarm",
    "oldfstat",
    "pause",
    "utime",
    "sys_stty",
    "sys_gtty",
    "access",
    "nice",
    "sys_ftime",
    "sync",
    "kill",
    "rename",
    "mkdir",
    "rmdir",
    "dup",
    "pipe",
    "times",
    "sys_prof",
    "brk",
    "setgid",
    "getgid",
    "signal",
    "geteuid",
    "getegid",
    "acct",
    "umount2",
    "sys_lock",
    "ioctl",
    "fcntl",
    "sys_mpx",
    "setpgid",
    "sys_ulimit",
    "sys_olduname",
    "umask",
    "chroot",
    "ustat",
    "dup2",
    "getppid",
    "getpgrp",
    "setsid",
    "sigaction",
    "sgetmask",
    "ssetmask",
    "setreuid",
    "setregid",
    "sigsuspend",
    "sigpending",
    "sethostname",
    "setrlimit",
    "getrlimit",
    "getrusage",
    "gettimeofday",
    "settimeofday",
    "getgroups",
    "setgroups",
    "sys_select",
    "symlink",
    "oldlstat",
    "readlink",
    "uselib",
    "swapon",
    "reboot",
    "readdir",
    "mmap",
    "munmap",
    "truncate",
    "ftruncate",
    "fchmod",
    "fchown",
    "getpriority",
    "setpriority",
    "sys_profil",
    "statfs",
    "fstatfs",
    "sys_ioperm",
    "socketcall",
    "syslog",
    "setitimer",
    "getitimer",
    "stat",
    "lstat",
    "fstat",
    "olduname",
    "sys_iopl",
    "vhangup",
    "sys_idle",
    "sys_vm86old",
    "wait4",
    "swapoff",
    "sysinfo",
    "ipc",
    "fsync",
    "sigreturn",
    "clone",
    "setdomainname",
    "uname",
    "cacheflush",
    "adjtimex",
    "mprotect",
    "sigprocmask",
    "sys_create_module",
    "init_module",
    "delete_module",
    "sys_get_kernel_syms",
    "quotactl",
    "getpgid",
    "fchdir",
    "bdflush",
    "sysfs",
    "personality",
    "sys_afs_syscall",
    "setfsuid",
    "setfsgid",
    "_llseek",
    "getdents",
    "_newselect",
    "flock",
    "msync",
    "readv",
    "writev",
    "getsid",
    "fdatasync",
    "_sysctl",
    "mlock",
    "munlock",
    "mlockall",
    "munlockall",
    "sched_setparam",
    "sched_getparam",
    "sched_setscheduler",
    "sched_getscheduler",
    "sched_yield",
    "sched_get_priority_max",
    "sched_get_priority_min",
    "sched_rr_get_interval",
    "nanosleep",
    "mremap",
    "setresuid",
    "getresuid",
    "sys_vm86",
    "sys_query_module",
    "poll",
    "nfsservctl",
    "setresgid",
    "getresgid",
    "prctl",
    "rt_sigreturn",
    "rt_sigaction",
    "rt_sigprocmask",
    "rt_sigpending",
    "rt_sigtimedwait",
    "rt_sigqueueinfo",
    "rt_sigsuspend",
    "pread64",
    "pwrite64",
    "chown",
    "getcwd",
    "capget",
    "capset",
    "sigaltstack",
    "sendfile",
    "getpmsg",
    "putpmsg",
    "vfork",
    "ugetrlimit",
    "mmap2",
    "truncate64",
    "ftruncate64",
    "stat64",
    "lstat64",
    "fstat64",
    "lchown32",
    "getuid32",
    "getgid32",
    "geteuid32",
    "getegid32",
    "setreuid32",
    "setregid32",
    "getgroups32",
    "setgroups32",
    "fchown32",
    "setresuid32",
    "getresuid32",
    "setresgid32",
    "getresgid32",
    "chown32",
    "setuid32",
    "setgid32",
    "setfsuid32",
    "setfsgid32",
    "pivot_root",
    "mincore",
    "madvise",
    "socket",
    "bind",
    "connect",
    "listen",
    "accept",
    "getsockname",
    "getpeername",
    "socketpair",
    "send",
    "sendto",
    "recv",
    "recvfrom",
    "shutdown",
    "setsockopt",
    "getsockopt",
    "sendmsg",
    "recvmsg",
    "semop",
    "semget",
    "semctl",
    "msgsnd",
    "msgrcv",
    "msgget",
    "msgctl",
    "shmat",
    "shmdt",
    "shmget",
    "shmctl",
    "getdents64",
    "fcntl64",
    "tux",
    "unused",
    "gettid",
    "readahead",
    "setxattr",
    "lsetxattr",
    "fsetxattr",
    "getxattr",
    "lgetxattr",
    "fgetxattr",
    "listxattr",
    "llistxattr",
    "flistxattr",
    "removexattr",
    "lremovexattr",
    "fremovexattr",
    "tkill",
    "sendfile64",
    "futex",
    "sched_setaffinity",
    "sched_getaffinity",
    "set_thread_area",
    "get_thread_area",
    "io_setup",
    "io_destroy",
    "io_getevents",
    "io_submit",
    "io_cancel",
    "fadvise64",
    "unused",
    "exit_group",
    "lookup_dcookie",
    "epoll_create",
    "epoll_ctl",
    "epoll_wait",
    "remap_file_pages",
    "set_tid_address",
    "timer_create",
    "timer_settime",
    "timer_gettime",
    "timer_getoverrun",
    "timer_delete",
    "clock_settime",
    "clock_gettime",
    "clock_getres",
    "clock_nanosleep",
    "statfs64",
    "fstatfs64",
    "tgkill",
    "utimes",
    "fadvise64_64",
    "vserver",
    "mbind",
    "get_mempolicy",
    "set_mempolicy",
    "mq_open",
    "mq_unlink",
    "mq_timedsend",
    "mq_timedreceive",
    "mq_notify",
    "mq_getsetattr",
    "kexec",
    "waitid",
    "add_key",
    "request_key",
    "keyctl",
    "ioprio_set",
    "ioprio_get",
    "inotify_init",
    "inotify_add_watch",
    "inotify_rm_watch",
    "unused",
    "migrate_pages",
    "openat",
    "mkdirat",
    "mknodat",
    "fchownat",
    "futimesat",
    "fstatat64",
    "unlinkat",
    "renameat",
    "linkat",
    "symlinkat",
    "readlinkat",
    "fchmodat",
    "faccessat",
    "pselect6",
    "ppoll",
    "unshare",
    "set_robust_list",
    "get_robust_list",
    "splice",
    "sync_file_range",
    "tee",
    "vmsplice",
    "move_pages",
    "getcpu",
    "epoll_pwait",
    "utimensat",
    "signalfd",
    "timerfd_create",
    "eventfd",
    "fallocate",
    "timerfd_settime",
    "timerfd_gettime",
    "signalfd4",
    "eventfd2",
    "epoll_create1",
    "dup3",
    "pipe2",
    "inotify_init1",
    "preadv",
    "pwritev",
    "rt_tgsigqueueinfo",
    "perf_event_open",
    "recvmmsg",
    "accept4",
    "fanotify_init",
    "fanotify_mark",
    "prlimit64",
    "name_to_handle_at",
    "open_by_handle_at",
    "clock_adjtime",
    "syncfs",
    "sendmmsg",
    "setns",
    "process_vm_readv",
    "process_vm_writev",
    "kcmp",
    "finit_module",
    "sched_getattr",
    "sched_setattr",
    "renameat2",
    "seccomp",
    "getrandom",
    "memfd_create",
    "bpf",
    "execveat",
    "userfaultfd",
    "membarrier",
    "mlock2",
    "copy_file_range",
    "preadv2",
    "pwritev2",
    "syscalls"
};

#endif
