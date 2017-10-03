package interactive;

public class Global {

    /**
     * DEBUG MODE
     */
    public static final boolean DEBUG = true;
    
    
    /**
     * return value
     * used for interactive module
     */
    public final static char SUCCESS            = 0;
    public final static char FAILURE            = 1;
    public final static char ID_ILLEGAL         = 2;
    public final static char PASSWD_ILLEGAL     = 3;
    public final static char PERMISSION_DENIED  = 4;
    public final static char USER_NOT_EXIST     = 5;
    public final static char OPERATION_ILLEGAL  = 6;
    public final static char STUDENT            = 100;
    public final static char TEACHER            = 101;
    
    
    /**
     * used for interactive module
     */
    // cookie 生存期，单位：秒
    public final static int COOKIE_LIVING = 6000;
    // support for mariadb
    public final static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    // database url with gp db
    public final static String JDBC_URL = "jdbc:mysql://localhost/gp?useUnicode=true&characterEncoding=UTF-8";
    // database url without db
    public final static String DB_URL = "jdbc:mysql://localhost/?useUnicode=true&characterEncoding=UTF-8";
    // database user
    public final static String DB_USER = "root";
    // database password
    public final static String DB_PASSWD = "dou/102";
    // root id for manager system
    public final static String ROOT_ID = "root";
    // root password for manager system
    public final static String ROOT_PASSWD = "root";
    
    
    /**
     * used for synamic test subsystem
     */
    // max error info
    public final static int MAX_ERRINFO = 81920;
    // sleep        maybe it will never be used
    public final static int SLEEP = 1000;
    // buffer size for read to make zip file
    public final static int BUFFER_SIZE = 65535;
    // dynamic test directory
    public final static String DYNAMIC_DIR = "./testdir/";
    // content path, content file is saved in path ./content/workid.txt
    public final static String CONTENT_DIR = "./content/";
    // referto path, referto file is saved in path ./referto/workid.txt
    public final static String REFERTO_DIR = "./referto/";
    // testcasein path, test-case-in file saved in path ./testcasein/workid.txt
    public final static String TESTCASEIN_DIR = "./testcasein/";
    // testcaseout path, test-case-out file saved in path ./testcaseout/workid.txt
    public final static String TESTCASEOUT_DIR = "./testcaseout/";
    // student upload code files saved in path ./uploadcode/workid/studentid.xxx
    public final static String UPLOADCODE_DIR = "./uploadcode/";
    // offline report path ./offline-report/workid/studentid
    public final static String OFFLINE_REPORT_DIR = "./offline-report/";
    // static report path ./static-report/workid/studentid
    public final static String STATIC_REPORT_DIR = "./static-report/";
    // dynamic report path ./dynamic-report/workid/studentid
    public final static String DYNAMIC_REPORT_DIR = "./dynamic-report/";
    // monitor path
    public final static String MONITOR = DYNAMIC_DIR + "monitor.exe";
    // offline test interface path
    public final static String OFFLINE_INTERFACE = "./offline/offline.zip";
    
    
    
    /**
     * cipher configuration
     */
    // SHA algorithm
    public static final String SHA_ALGORITHM = "SHA-256";
    // RSA algorithm
    public static final String RSA_ALGORITHM = "RSA";
    // RSA key size
    public static final int RSA_KEYSIZE = 2048;
    // RSA public key path
    public static final String PUBLIC_KEY_FILE = "./pub-rsakey";
    // RSA private key path
    public static final String PRIVATE_KEY_FILE = "./rsakey";
    
    
    
    /**
     * parse report configuration
     */
    // shell path
    public final static String SHELL_PATH = "D:/Git/git-bash.exe";
    // private rsa key
    public final static String PRIVATE_RSA_KEY = "./rsa.key";
    // usage.usg filename
    public final static String USAGE_USG_FILENAME = "usage.usg";
    // openssl cmd
    public final static String OPENSSL_DECRYPT_CMD = "openssl rsautl -decrypt -inkey "+PRIVATE_RSA_KEY+" -out "+USAGE_USG_FILENAME+" -in ";
    
    
    
    /**
     * compile configuration
     */
    // correct table for load class by java-reflect
    public final static String AUTOCRCT_C_CFG_PATH = "./autoc/auto-crct.cfg";
    // correct class path
    public final static String AUTOCRCT_C_CLASS_PATH = "./autoc/";
 // correct table for load class by java-reflect
    public final static String AUTOCRCT_CPP_CFG_PATH = "./autocpp/auto-crct.cfg";
    // correct class path
    public final static String AUTOCRCT_CPP_CLASS_PATH = "./autocpp/";
 // correct table for load class by java-reflect
    public final static String AUTOCRCT_JAVA_CFG_PATH = "./autojava/auto-crct.cfg";
    // correct class path
    public final static String AUTOCRCT_JAVA_CLASS_PATH = "./autojava/";
    
    
    
    /**
     * create tables
     */
    public final static String CR_TEACHER = "CREATE TABLE teacher ("
            + "t_id CHAR(8) not NULL,"
            + "t_shadow CHAR(64) not NULL,"
            + "t_salt CHAR(32) not NULL,"
            + "t_name VARCHAR(16),"
            + "t_email VARCHAR(32),"
            + "t_resume VARCHAR(256),"
            + "PRIMARY KEY(t_id)"
            + ") engine=innodb";

    public final static String CR_CLASS = "CREATE TABLE class ("
            + "c_id CHAR(8) not NULL,"
            + "c_name VARCHAR(20) not NULL,"
            + "c_resume VARCHAR(256),"
            + "t_id CHAR(8) not NULL,"
            + "PRIMARY KEY(c_id),"
            + "FOREIGN KEY(t_id) REFERENCES teacher(t_id) ON DELETE CASCADE"
            + ") engine=innodb";

    public final static String CR_STUDENT = "CREATE TABLE student ("
            + "s_id CHAR(8) not NULL,"
            + "s_shadow CHAR(64) not NULL,"
            + "s_salt CHAR(32) not NULL,"
            + "s_name VARCHAR(16),"
            + "s_email VARCHAR(32),"
            + "s_resume VARCHAR(256),"
            + "PRIMARY KEY(s_id)"
            + ") engine=innodb";

    public final static String CR_CSMAP = "CREATE TABLE csmap ("
            + "s_id CHAR(8) not NULL,"
            + "c_id CHAR(8) not NULL,"
            + "FOREIGN KEY(s_id) REFERENCES student(s_id) ON DELETE CASCADE,"
            + "FOREIGN KEY(c_id) REFERENCES class(c_id) ON DELETE CASCADE"
            + ") engine=innodb";

    public final static String CR_WORK = "CREATE TABLE work ("
            + "w_id CHAR(8) not NULL,"
            + "w_title VARCHAR(64) not NULL,"
            + "w_content VARCHAR(64) not NULL,"
            + "w_codetype VARCHAR(8) not NULL,"
            + "w_referto VARCHAR(64),"
            + "w_keylines VARCHAR(64),"
            + "w_testcasein VARCHAR(64),"
            + "w_testcaseout VARCHAR(64),"
            + "w_limcpu INTEGER not NULL default 0,"
            + "w_limmem INTEGER not NULL default 0,"
            + "w_dotest INTEGER not NULL default 0,"
            + "w_time BIGINT not NULL,"
            + "c_id CHAR(8) not NULL,"
            + "PRIMARY KEY(w_id),"
            + "FOREIGN KEY(c_id) REFERENCES class(c_id) ON DELETE CASCADE"
            + ") engine=innodb";

    public final static String CR_REPLY = "CREATE TABLE reply ("
            + "r_id CHAR(8) not NULL,"
            + "r_source VARCHAR(64) not NULL,"
            + "r_staticscore INTEGER not NULL default 0,"
            + "r_dynamicscore INTEGER not NULL default 0,"
            + "r_staticreport VARCHAR(64),"
            + "r_dynamicreport VARCHAR(64),"
            + "r_time BIGINT not NULL,"
            + "s_id CHAR(8) not NULL,"
            + "w_id CHAR(8) not NULL,"
            + "PRIMARY KEY(r_id),"
            + "FOREIGN KEY(s_id) REFERENCES student(s_id) ON DELETE CASCADE,"
            + "FOREIGN KEY(w_id) REFERENCES work(w_id) ON DELETE CASCADE"
            + ") engine=innodb";
    
    
    
    
    
    /**************************************************/
    /*
     *      DEBUG function
     */
    /**************************************************/
    /**
     * print message for test
     */
    public static void PRERR(String s) {
        if (DEBUG) { System.out.println(s); }
    }
    
    public static void PRERR(boolean b) {
        if (DEBUG) { System.out.println(b); }
    }
    
    public static void PRERR(int i) {
        if (DEBUG) { System.out.println(i); }
    }
    
    public static void PRERR(double d) {
        if (DEBUG) { System.out.println(d); }
    }
}
