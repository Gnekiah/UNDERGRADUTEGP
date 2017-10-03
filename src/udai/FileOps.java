package udai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import interactive.Global;

public class FileOps {
    
    // read file
    public static String loadFile(String path) {
        return loadFile(new File(path));
    }
    
    // read file
    public static String loadFile(File f) {
        String ret = "";
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while((line=br.readLine()) != null) {
                sb.append(line+"\n");
            }
            ret = sb.toString();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return ret;
    }
    
    // save file, used in file upload
    public static boolean saveFile(String path, String filename, InputStream is) {
        try {
            File f = new File(path, filename);
            FileOutputStream fos = new FileOutputStream(f);
            byte[] b = new byte[Global.BUFFER_SIZE];
            int flag = 0;
            while((flag = is.read(b)) != -1) {
                fos.write(b, 0, flag);
            }
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return false;
    }
    
    // save file
    public static boolean saveFile(File f, String s) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(s);
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return false;
        }
        finally {
            try {
                bw.close();
            } catch (IOException e) {
                if (Global.DEBUG)
                    e.printStackTrace();
            }
        }
        return true;
    }
    
    // delete file
    public static void delFile(String s) {
        File f = new File(s);
        if (f.isFile())
            f.delete();
    }
    
    // delete file
    public static void delFile(File f) {
        if (f.isFile())
            f.delete();
    }
    
    // download offline test interface for student
    public static void getOffline(OutputStream os) {
        File f = new File(Global.OFFLINE_INTERFACE);
        if (!f.isFile()) {
            return; 
        }
        try {
            FileInputStream fis = new FileInputStream(f);
            byte[] b = new byte[Global.BUFFER_SIZE];
            int flag = 0;
            while((flag=fis.read(b)) != -1) {
                os.write(b, 0, flag);
            }
            os.flush();
            fis.close();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    // used for teacher download a work's all replies
    public static void getPackages(String wid, OutputStream os) {
        File fdir = new File(Global.UPLOADCODE_DIR + wid);
        if (!fdir.isDirectory()) 
            return;
        File[] files = fdir.listFiles();
        ZipOutputStream zos = new ZipOutputStream(os);
        for (File f : files) {
            try {
                doZip(f, zos);
            } catch (IOException e) {
                if (Global.DEBUG)
                    e.printStackTrace();
            }
        }
        try {
            zos.close();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    private static void doZip(File f, ZipOutputStream zos) throws IOException {
        if (f.exists()) {
            if (f.isFile()) {
                zos.putNextEntry(new ZipEntry(f.getName()));
                FileInputStream fis = new FileInputStream(f);
                byte[] buffer = new byte[Global.BUFFER_SIZE];
                int flag = 0;
                while ((flag = fis.read(buffer)) != -1) { 
                    zos.write(buffer, 0, flag);
                }
                zos.flush();
                fis.close();
            } else {
                String currDir = f.getName() + "/";
                zos.putNextEntry(new ZipEntry(currDir));
                File[] files = f.listFiles();
                for (File ftmp : files) {
                    doZip(ftmp, zos);
                }
            }
        }
    }

}
