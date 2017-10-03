import os


def func1():
    calls = []
    callid = 0
    with open("unistd_32.h", "rt") as f:
        for i in f:
            s = ""
            if i[:13] == "#define __NR_":
                s = "    " + "0,      /* " + str(callid) + ', ' + i.split(" ")[1][5:] + " */\n"
                calls.append(s)
            else:
                s = "    // \n"
                calls.append(s)
            callid += 1
    with open("tmpf.txt", "at") as f:
        for i in calls:
            f.write(i)


def func2():
    calls = []
    with open("syscall_auth_32.txt", "rt") as f:
        for i in f:
            s = ""
            ils = i.split(" ")
            if ils[-1] == "*/\n":
                s = "    \"" +ils[-2] + "\"\n"
            else:
                s = "    \"" +ils[-3] + "\"\n"
            calls.append(s)
    with open("syscall_desc_32.txt", "wt") as f:
        for i in calls:
            f.write(i)


func2()
