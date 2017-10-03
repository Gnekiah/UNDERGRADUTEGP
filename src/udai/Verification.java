package udai;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.Cookie;

import dao.StudentDao;
import dao.TeacherDao;
import interactive.Global;

/**
 * verifying cookies and login
 * @author Ekira
 *
 */

public class Verification {
    
    // verify for general user
    public static char doCookiesVer(Cookie cookies[]) {
        return doCookiesVer(cookies, false);
    }
    
    // verify for root user
    public static char doCookiesVer(Cookie cookies[], boolean root) {
        String username = "";
        String password = "";
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) {
                username = c.getValue();
            }
            if (c.getName().equals("password")) {
                password = c.getValue();
            }
        }
        if (root)
            if (!username.equals(Global.ROOT_ID))
                return Global.FAILURE;
        return cookieVer(username, password);
    }
    
    /*
    public static String cookieGen(String name) {
        long tstp = System.currentTimeMillis() / (Global.COOKIE_LIVING * 1000);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        md.update((name+tstp).getBytes());
        byte[]b = md.digest();
        return bytes2HexString(b);
    }*/
    
    
    public static String cookieGen(String name) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        String shadow = null;
        char type = accountType(name); 
        if (type == Global.STUDENT)
            shadow = StudentDao.get(name).getShadow();
        else if (type == Global.TEACHER)
            shadow = TeacherDao.get(name).getShadow();
        else return null;
        md.update((name+shadow).getBytes());
        byte[]b = md.digest();
        return bytes2HexString(b);
    }

    
    public static char cookieVer(String name, String value) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return Global.FAILURE;
        }
        String shadow = null;
        char type = accountType(name); 
        if (type == Global.STUDENT)
            shadow = StudentDao.get(name).getShadow();
        else if (type == Global.TEACHER)
            shadow = TeacherDao.get(name).getShadow();
        else return Global.FAILURE;
        md.update((name+shadow).getBytes());
        if (bytes2HexString(md.digest()).equals(value))
            return type;
        return Global.FAILURE;
    }
    
    /*
    public static char cookieVer(String name, String value) {
        long tstp1 = System.currentTimeMillis() / (Global.COOKIE_LIVING * 1000);
        long tstp2 = tstp1 - 1;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return Global.FAILURE;
        }
        md.update((name+tstp1).getBytes());
        if (bytes2HexString(md.digest()).equals(value))
            return accountType(name);
        md.update((name+tstp2).getBytes());
        if (bytes2HexString(md.digest()).equals(value))
            return accountType(name);
        return Global.FAILURE;
    }*/
    
    
    public static char loginVer(String id, String passwd) {
        String shadow = shadowGen(id, passwd);
        if (shadow == null)
            return Global.FAILURE;
        switch(accountType(id)) {
        case Global.STUDENT:
            if (StudentDao.get(id).getShadow().equals(shadow))
                return Global.STUDENT;
            break;
        case Global.TEACHER:
            if (TeacherDao.get(id).getShadow().equals(shadow))
                return Global.TEACHER;
            break;
        case Global.USER_NOT_EXIST:
            return Global.USER_NOT_EXIST;
        }
        return Global.FAILURE;
    }
    
    
    public static String shadowGen(String id, String passwd, String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        if (salt == null) 
            return null;
        md.update((salt + id + passwd).getBytes());
        return bytes2HexString(md.digest());
    }
    
    
     public static String saltGen() {
        byte[] bs = new byte[16];
        Random r = new Random(System.currentTimeMillis());
        r.nextBytes(bs);
        return bytes2HexString(bs);
    }
     
     
     public static char accountType(String id) {
         if (TeacherDao.get(id) != null)
             return Global.TEACHER;
         if (StudentDao.get(id) != null)
             return Global.STUDENT;
         return Global.USER_NOT_EXIST;
     }
     
     
     private static String shadowGen(String id, String passwd) {
         return shadowGen(id, passwd, saltGet(id));
     }  
    
     
    private static String saltGet(String id) {
        switch(accountType(id)) {
        case Global.STUDENT:
            return StudentDao.get(id).getSalt();
        case Global.TEACHER:
            return TeacherDao.get(id).getSalt();
        }
        return null;
    }
    
    
    
    
    
    private static String bytes2HexString(byte[] bs) {
        String hexStr = "";
        String hexTmp = "";
        for (byte b : bs) {
            hexTmp = Integer.toHexString(b & 0xFF);
            if (hexTmp.length() == 1) 
                hexTmp = '0' + hexTmp;
            hexStr += hexTmp.toUpperCase();
        }
        return hexStr;
    }
    
    
    
    /*
    public void test() {
        System.out.println(saltGen());
    }
    
    public static void main(String[] s) throws Exception {
        System.out.println(Verification.saltGen());
        //Verification.CookieVer(null, null);
    }*/
    
}
