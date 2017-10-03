##program return

VALUE_LACK
    Need options.
    e.g. <EXEC> target.exe 0 0 0 0 0 0 0 0 stdin.in stdout.out 2

VALUE_ERROR
    You should give <EXEC> correct values.

PID_CREATED_FAILED
    While fork() failed, program return.

PID_EXEC_ERROR
    In normal, it would not return the value except while execv(pgm, NULL) error, the return value point out target program return for errors.

SUCCESS
    It means execv(pgm, NULL) is successful.

SYSCALL_NOAUTH
    While target program call an illigal system call, then kill the program and write current rusage and call count to files.


