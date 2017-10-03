package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import interactive.Global;
import udai.CipherDesu;

public class CipherTest {
    
    public static String runopenssl(String[] cmd, File wd) {
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
    
    public static void main(String[] s) throws Exception {
        System.out.println(new File("./").getAbsolutePath());
        String[] cmd = {"D:/Git/git-bash", "-c", "openssl rsautl -decrypt -in usage.rpt -out usage.usg -inkey rsa.key"};
        String errInfo = CipherTest.runopenssl(cmd, new File("C:/Users/Ekira/Desktop/gp/subsys_interactive"));
        System.out.println(errInfo);
    }

}
