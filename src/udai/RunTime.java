package udai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import interactive.Global;

public class RunTime {
    
    public static void rptDecrypt(String wid, String sid) {
        String[] cmd = new String[3];
        cmd[0] = Global.SHELL_PATH;
        cmd[1] = "-c";
        cmd[2] = Global.OPENSSL_DECRYPT_CMD + Global.OFFLINE_REPORT_DIR + wid + "/" + sid;
        doRunTime(cmd, "./");
        File f = new File(Global.USAGE_USG_FILENAME); 
        if (f.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(f);
                FileOutputStream fos = new FileOutputStream(Global.OFFLINE_REPORT_DIR + wid + "/" + sid);
                int flag = 0;
                byte[] b = new byte[Global.BUFFER_SIZE];
                while ((flag=fis.read(b)) != -1) {
                    fos.write(b, 0, flag);
                }
                fos.flush();
                fos.close();
                fis.close();
                f.delete();
            } catch (IOException e) {
                if (Global.DEBUG)
                    e.printStackTrace();
            }
        }
    }

    public static String doRunTime(String[] cmd, String wd) {
        return doRunTime(cmd, new File(wd));
    }
    
    public static String doRunTime(String[] cmd, File wd) {
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
