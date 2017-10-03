#ifndef _SYSCALL_RULE_32_H
#define _SYSCALL_RILE_32_H


#define CALLED  orig_eax
#define RETURN  eax
#define ARG1    ebx
#define ARG2    ecx
#define ARG3    edx
#define ARG4    esi
#define ARG5    edi
#define ARG6    ebp

static const int CALL_AMOUNT = 384;

/* count for call times */
static int callcnt[384] = {0};


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
    1,      /* 35, sys_gtty */
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
    0,      /* 82, sys_oldselect */
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
    0,      /* 188, sys_getpmsg */
    0,      /* 189, sys_putpmsg */
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
    2,      /* 220, getdents64 */
    2,      /* 221, fcntl64 */
    2,      /* 222, tux */
    2,      /* 223 is unused */
    2,      /* 224, gettid */
    2,      /* 225, readahead */
    2,      /* 226, setxattr */
    2,      /* 227, lsetxattr */
    2,      /* 228, fsetxattr */
    2,      /* 229, getxattr */
    2,      /* 230, lgetxattr */
    2,      /* 231, fgetxattr */
    0,      /* 232, listxattr */
    2,      /* 233, llistxattr */
    2,      /* 234, flistxattr */
    2,      /* 235, removexattr */
    2,      /* 236, lremovexattr */
    1,      /* 237, fremovexattr */
    1,      /* 238, tkill */
    1,      /* 239, sendfile64 */
    0,      /* 240, futex */
    0,      /* 241, sched_setaffinity */
    0,      /* 242, sched_getaffinity */
    0,      /* 243, set_thread_area */
    0,      /* 244, get_thread_area */
    0,      /* 245, io_setup */
    0,      /* 246, io_destroy */
    0,      /* 247, io_getevents */
    2,      /* 248, io_submit */
    2,      /* 249, io_cancel */
    0,      /* 250, fadvise64 */
    0,      /* 251 is unused */
    1,      /* 252, exit_group */
    0,      /* 253, lookup_dcookie */
    0,      /* 254, epoll_create */
    0,      /* 255, epoll_ctl */
    0,      /* 256, epoll_wait */
    0,      /* 257, remap_file_pages */
    0,      /* 258, set_tid_address */
    0,      /* 259, timer_create */
    0,      /* 260, timer_settime */
    0,      /* 261, timer_gettime */
    0,      /* 262, timer_getoverrun */
    0,      /* 263, timer_delete */
    0,      /* 264, clock_settime */
    0,      /* 265, clock_gettime */
    0,      /* 266, clock_getres */
    2,      /* 267, clock_nanosleep */
    0,      /* 268, statfs64 */
    0,      /* 269, fstatfs64 */
    0,      /* 270, tgkill */
    0,      /* 271, utimes */
    0,      /* 272, fadvise64_64 */
    0,      /* 273, vserver */
    0,      /* 274, mbind */
    0,      /* 275, get_mempolicy */
    0,      /* 276, set_mempolicy */
    0,      /* 277, mq_open */
    0,      /* 278, mq_unlink */
    0,      /* 279, mq_timedsend */
    0,      /* 280, mq_timedreceive */
    0,      /* 281, mq_notify */
    0,      /* 282, mq_getsetattr */
    0,      /* 283, kexec_load */
    0,      /* 284, waitid */
    0,      /* 285, add_key */
    0,      /* 286, request_key */
    0,      /* 287, keyctl */
    0,      /* 288, ioprio_set */
    0,      /* 289, ioprio_get */
    0,      /* 290, inotify_init */
    0,      /* 291, inotify_add_watch */
    0,      /* 292, inotify_rm_watch */
    0,      /* 293 is unused */
    0,      /* 294, migrate_pages */
    0,      /* 295, openat */
    2,      /* 296, mkdirat */
    2,      /* 297, mknodat */
    0,      /* 298, fchownat */
    1,      /* 299, futimesat */
    0,      /* 300, fstatat64 */
    0,      /* 301, unlinkat */
    1,      /* 302, renameat */
    0,      /* 303, linkat */
    0,      /* 304, symlinkat */
    0,      /* 305, readlinkat */
    0,      /* 306, fchmodat */
    0,      /* 307, faccessat */
    0,      /* 308, pselect6 */
    0,      /* 309, ppoll */
    0,      /* 310, unshare */
    0,      /* 311, set_robust_list */
    0,      /* 312, get_robust_list */
    0,      /* 313, splice */
    0,      /* 314, sync_file_range */
    0,      /* 315, tee */
    0,      /* 316, vmsplice */
    0,      /* 317, move_pages */
    0,      /* 318, getcpu */
    0,      /* 319, epoll_pwait */
    0,      /* 320, utimensat */
    0,      /* 321, signalfd */
    0,      /* 322, timerfd_create */
    1,      /* 323, eventfd */
    1,      /* 324, fallocate */
    1,      /* 325, timerfd_settime */
    1,      /* 326, timerfd_gettime */
    1,      /* 327, signalfd4 */
    1,      /* 328, eventfd2 */
    1,      /* 329, epoll_create1 */
    1,      /* 330, dup3 */
    1,      /* 331, pipe2 */
    1,      /* 332, inotify_init1 */
    1,      /* 333, preadv */
    1,      /* 334, pwritev */
    1,      /* 335, rt_tgsigqueueinfo */
    0,      /* 336, perf_event_open */
    0,      /* 337, fanotify_init */
    0,      /* 338, fanotify_mark */
    0,      /* 339, prlimit64 */
    0,      /* 340, socket */
    0,      /* 341, bind */
    0,      /* 342, connect */
    0,      /* 343, listen */
    0,      /* 344, accept */
    0,      /* 345, getsockname */
    0,      /* 346, getpeername */
    0,      /* 347, socketpair */
    0,      /* 348, send */
    0,      /* 349, sendto */
    0,      /* 350, recv */
    3,      /* 351, recvfrom */
    0,      /* 352, shutdown */
    0,      /* 353, setsockopt */
    0,      /* 354, getsockopt */
    0,      /* 355, sendmsg */
    0,      /* 356, recvmsg */
    0,      /* 357, recvmmsg */
    2,      /* 358, accept4 */
    0,      /* 359, name_to_handle_at */
    0,      /* 360, open_by_handle_at */
    0,      /* 361, clock_adjtime */
    0,      /* 362, syncfs */
    0,      /* 363, sendmmsg */
    0,      /* 364, setns */
    0,      /* 365, process_vm_readv */
    0,      /* 366, process_vm_writev */
    0,      /* 367, kcmp */
    0,      /* 368, finit_module */
    0,      /* 369, sched_getattr */
    0,      /* 370, sched_setattr */
    0,      /* 371, renameat2 */
    0,      /* 372, seccomp */
    0,      /* 373, getrandom */
    0,      /* 374, memfd_create */
    0,      /* 375, bpf */
    0,      /* 376, execveat */
    0,      /* 377, userfaultfd */
    0,      /* 378, membarrier */
    0,      /* 379, mlock2 */
    0,      /* 380, copy_file_range */
    0,      /* 381, preadv2 */
    1,      /* 382, pwritev2 */
    0,      /* 383, syscalls */
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
    "sys_gtty",
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
    "sys_oldselect",
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
    "sys_getpmsg",
    "sys_putpmsg",
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
    "kexec_load",
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
    "fanotify_init",
    "fanotify_mark",
    "prlimit64",
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
    "recvmmsg",
    "accept4",
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
