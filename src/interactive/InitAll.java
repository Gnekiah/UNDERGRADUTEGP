package interactive;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import dao.DaoConfig;

public class InitAll {
    public static void init() {
        Connection conn = DaoConfig.getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                if (Global.DEBUG)
                    e.printStackTrace();
            }
        }
        initDir(Global.CONTENT_DIR);
        initDir(Global.DYNAMIC_DIR);
        initDir(Global.REFERTO_DIR);
        initDir(Global.TESTCASEIN_DIR);
        initDir(Global.TESTCASEOUT_DIR);
        initDir(Global.UPLOADCODE_DIR);
        initDir(Global.OFFLINE_REPORT_DIR);
        initDir(Global.STATIC_REPORT_DIR);
        initDir(Global.DYNAMIC_REPORT_DIR);
    }
    
    private static void initDir(String dir) {
        File f = new File(dir);
        if (!f.isDirectory()) {
            f.mkdir();
        }
    }
    
}
