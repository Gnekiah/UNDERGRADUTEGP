package compile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import dynio.Global;

public class Compile {
    
    public static String compileC(String src, String wd) {
        String[] cmd = {"/bin/bash", "-c", "gcc " + src};
        return compile(cmd, new File(wd));
    }
    
    public static String compileCpp(String src, String wd) {
        String[] cmd = {"/bin/bash", "-c", "g++ " + src};
        return compile(cmd, new File(wd));
    }
    
    public static String compileJava(String src, String wd) {
        String[] cmd = {"/bin/bash", "-c", "javac -d ./ " + src};
        return compile(cmd, new File(wd));
    }
    
    
    public static String compile(String[] cmd, File wd) {
        Process pro = null;
        Runtime runtime = Runtime.getRuntime();
        BufferedReader brErr = null;
        String ret = null;
        char[] c = new char[Global.MAX_ERRINFO];
        try {
            pro = runtime.exec(cmd, null, wd);
            pro.waitFor();
            brErr = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
            int len = brErr.read(c);
            if (len > -1)
                ret = String.valueOf(c, 0, len);
            brErr.close();
        }
        catch(Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return ret;
    }

}
