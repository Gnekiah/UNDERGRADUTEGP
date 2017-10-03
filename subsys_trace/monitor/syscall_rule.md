##syscall rule

0 - root/(do not trace) 不受限制
1 - trace on loosen     提供除了可能威胁到系统安全之外的所有系统调用
                        e.g.能够修改文件的属性，例如创建日期、拥有者等
2 - trace on normally   常规的操作
                        e.g.能够增删读写文件、目录，能够使用socket连接网络
3 - trace on strictly   只提供少量能使程序运行的系统调用，不提供文件读写
                        e.g.程序只对输入的值执行某些运算，并输出到屏幕


一、进程控制：
0   fork                    创建一个新进程
0   clone                   按指定条件创建子进程
0   execve                  运行可执行文件
3   exit                    中止进程
3   _exit                   立即中止当前进程
2   getdtablesize           进程所能打开的最大文件数
1   getpgid                 获取指定进程组标识号
0   setpgid                 设置指定进程组标志号
1   getpgrp                 获取当前进程组标识号
0   setpgrp                 设置当前进程组标志号
1   getpid                  获取进程标识号
1   getppid                 获取父进程标识号
1   getpriority             获取调度优先级
0   setpriority             设置调度优先级
0   modify_ldt              读写进程的本地描述表
0   nanosleep               使进程睡眠指定的时间
0   nice                    改变分时进程的优先级
1   pause                   挂起进程，等待信号
0   personality             设置进程运行域
0   prctl                   对进程进行特定操作
0   ptrace                  进程跟踪
0   sched_get_priority_max  取得静态优先级的上限
0   sched_get_priority_min  取得静态优先级的下限
0   sched_getparam          取得进程的调度参数
0   sched_getscheduler      取得指定进程的调度策略
0   sched_rr_get_interval   取得按RR算法调度的实时进程的时间片长度
0   sched_setparam          设置进程的调度参数
0   sched_setscheduler      设置指定进程的调度策略和参数
0   sched_yield             进程主动让出处理器,并将自己等候调度队列队尾
0   vfork                   创建一个子进程，以供执行新程序，常与execve等同时使用
0   wait                    等待子进程终止
0   wait3                   参见wait
0   waitpid                 等待指定子进程终止
0   wait4                   参见waitpid
1   capget                  获取进程权限
0   capset                  设置进程权限
1   getsid                  获取会晤标识号
0   setsid                  设置会晤标识号



二、文件系统控制
1、文件读写操作
2   fcntl       文件控制
2   open        打开文件
2   creat       创建新文件
2   close       关闭文件描述字
2   read        读文件
2   write       写文件
2   readv       从文件读入数据到缓冲数组中
2   writev      将缓冲数组里的数据写入文件
2   pread       对文件随机读
2   pwrite      对文件随机写
2   lseek       移动文件指针
2   _llseek     在64位地址空间里移动文件指针
2   dup         复制已打开的文件描述字
2   dup2        按指定条件复制文件描述字
2   flock       文件加/解锁
2   poll        I/O多路转换
2   truncate    截断文件
2   ftruncate   参见truncate
1   umask       设置文件权限掩码
2   fsync       把文件在内存中的部分写回磁盘

2、文件系统操作
2   access      确定文件的可存取性
2   chdir       改变当前工作目录
2   fchdir      参见chdir
1   chmod       改变文件方式
1   fchmod      参见chmod
1   chown       改变文件的属主或用户组
1   fchown      参见chown
1   lchown      参见chown
0   chroot      改变根目录
2   stat        取文件状态信息
2   lstat       参见stat
2   fstat       参见stat
1   statfs      取文件系统信息
1   fstatfs     参见statfs
2   readdir     读取目录项
2   getdents    读取目录项
2   mkdir       创建目录
2   mknod       创建索引节点
2   rmdir       删除目录
2   rename      文件改名
2   link        创建链接
2   symlink     创建符号链接
2   unlink      删除链接
2   readlink    读符号链接的值
0   mount       安装文件系统
0   umount      卸下文件系统
1   ustat       取文件系统信息
1   utime       改变文件的访问修改时间
1   utimes      参见utime
0   quotactl    控制磁盘配额



三、系统控制
0   ioctl               I/O总控制函数
0   _sysctl             读/写系统参数
0   acct                启用或禁止进程记账
1   getrlimit           获取系统资源上限
0   setrlimit           设置系统资源上限
1   getrusage           获取系统资源使用情况
0   uselib              选择要使用的二进制函数库
0   ioperm              设置端口I/O权限
0   iopl                改变进程I/O权限级别
0   outb                低级端口操作
0   reboot              重新启动
0   swapon              打开交换文件和设备
0   swapoff             关闭交换文件和设备
0   bdflush             控制bdflush守护进程
1   sysfs               取核心支持的文件系统类型
1   sysinfo             取得系统信息
0   adjtimex            调整系统时钟
0   alarm               设置进程的闹钟
1   getitimer           获取计时器值
0   setitimer           设置计时器值
1   gettimeofday        取时间和时区
0   settimeofday        设置时间和时区
0   stime               设置系统日期和时间
1   time                取得系统时间
1   times               取进程运行时间
1   uname               获取当前UNIX系统的名称、版本和主机等信息
0   vhangup             挂起当前终端
0   nfsservctl          对NFS守护进程进行控制
0   vm86                进入模拟8086模式
0   create_module       创建可装载的模块项
0   delete_module       删除可装载的模块项
0   init_module         初始化模块
0   query_module        查询模块信息
0   *get_kernel_syms    取得核心符号,已被query_module代替



四、内存管理
0   brk         改变数据段空间的分配
0   sbrk        参见brk
0   mlock       内存页面加锁
0   munlock     内存页面解锁
0   mlockall    调用进程所有内存页面加锁
0   munlockall  调用进程所有内存页面解锁
0   mmap        映射虚拟内存页
0   munmap      去除内存页映射
0   mremap      重新映射虚拟内存地址
0   msync       将映射内存中的数据写回磁盘
0   mprotect    设置内存映像保护
0   getpagesize 获取页面大小
0   sync        将内存缓冲区数据写回硬盘
0   cacheflush  将指定缓冲区中的内容写回磁盘



五、网络管理
2   getdomainname   取域名
0   setdomainname   设置域名
2   gethostid       获取主机标识号
0   sethostid       设置主机标识号
2   gethostname     获取本主机名称
0   sethostname     设置主机名称



六、socket控制
2   socketcall  socket系统调用
2   socket      建立socket
2   bind        绑定socket到端口
2   connect     连接远程主机
2   accept      响应socket连接请求
2   send        通过socket发送信息
2   sendto      发送UDP信息
2   sendmsg     参见send
2   recv        通过socket接收信息
2   recvfrom    接收UDP信息
2   recvmsg     参见recv
2   listen      监听socket端口
2   select      对多路同步I/O进行轮询
2   shutdown    关闭socket上的连接
2   getsockname 取得本地socket名字
2   getpeername 获取通信对方的socket名字
2   getsockopt  取端口设置
2   setsockopt  设置端口参数
2   sendfile    在文件或端口间传输数据
2   socketpair  创建一对已联接的无名socket



七、用户管理
1   getuid      获取用户标识号
0   setuid      设置用户标志号
1   getgid      获取组标识号
0   setgid      设置组标志号
1   getegid     获取有效组标识号
0   setegid     设置有效组标识号
1   geteuid     获取有效用户标识号
0   seteuid     设置有效用户标识号
0   setregid    分别设置真实和有效的的组标识号
0   setreuid    分别设置真实和有效的用户标识号
1   getresgid   分别获取真实的,有效的和保存过的组标识号
0   setresgid   分别设置真实的,有效的和保存过的组标识号
1   getresuid   分别获取真实的,有效的和保存过的用户标识号
0   setresuid   分别设置真实的,有效的和保存过的用户标识号
0   setfsgid    设置文件系统检查时使用的组标识号
0   setfsuid    设置文件系统检查时使用的用户标识号
1   getgroups   获取后补组标志清单
0   setgroups   设置后补组标志清单



八、进程间通信
0   ipc 进程间通信总控制调用

1、信号
0   sigaction       设置对指定信号的处理方法
0   sigprocmask     根据参数对信号集中的信号执行阻塞/解除阻塞等操作
0   sigpending      为指定的被阻塞信号设置队列
0   sigsuspend      挂起进程等待特定信号
0   signal          参见signal
0   kill            向进程或进程组发信号
0   *sigblock       向被阻塞信号掩码中添加信号,已被sigprocmask代替
0   *siggetmask     取得现有阻塞信号掩码,已被sigprocmask代替
0   *sigsetmask     用给定信号掩码替换现有阻塞信号掩码,已被sigprocmask代替
0   *sigmask        将给定的信号转化为掩码,已被sigprocmask代替
0   *sigpause       作用同sigsuspend,已被sigsuspend代替
0   sigvec          为兼容BSD而设的信号处理函数,作用类似sigaction
0   ssetmask        ANSI C的信号处理函数,作用类似sigaction

2、消息
0   msgctl  消息控制操作
0   msgget  获取消息队列
0   msgsnd  发消息
0   msgrcv  取消息

3、管道
0   pipe    创建管道

4、信号量
1   semctl  信号量控制
1   semget  获取一组信号量
1   semop   信号量操作

5、共享内存
0   shmctl      控制共享内存
0   shmget      获取共享内存
0   shmat       连接共享内存
0   shmdt       拆卸共享内存将给定的信号转化为掩码,已被sigprocmask代替
0   *sigpause   作用同sigsuspend,已被sigsuspend代替
0   sigvec      为兼容BSD而设的信号处理函数,作用类似sigaction
0   ssetmask    ANSI C的信号处理函数,作用类似sigaction
