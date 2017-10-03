package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataunit.Work;
import interactive.Global;

public class WorkDao {
    public static boolean add(final Work w) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into work value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, w.getId());
            ps.setString(2, w.getTitle());
            ps.setString(3, w.getContent());
            ps.setString(4, w.getCodetype());
            ps.setString(5, w.getReferto());
            ps.setString(6, w.getKeylines());
            ps.setString(7, w.getTestcasein());
            ps.setString(8, w.getTestcaseout());
            ps.setInt(9, w.getLimcpu());
            ps.setInt(10, w.getLimmem());
            ps.setInt(11, w.getDotest());
            ps.setLong(12, w.getTime());
            ps.setString(13, w.getCid());
            ps.execute();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public static Work getById(final String id) {
        Connection conn = DaoConfig.getConnection();
        Work w = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from work where w_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                w = new Work();
                w.setId(rs.getString("w_id"));
                w.SetTitle(rs.getString("w_title"));
                w.setContent(rs.getString("w_content"));
                w.setCodetype(rs.getString("w_codetype"));
                w.setReferto(rs.getString("w_referto"));
                w.setKeylines(rs.getString("w_keylines"));
                w.setTestcasein(rs.getString("w_testcasein"));
                w.setTestcaseout(rs.getString("w_testcaseout"));
                w.setLimcpu(rs.getInt("w_limcpu"));
                w.setLimmem(rs.getInt("w_limmem"));
                w.setDotest(rs.getInt("w_dotest"));
                w.setTime(rs.getLong("w_time"));
                w.setCid(rs.getString("c_id"));
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return w;
    }
    
    
    public static List<Work> getByCid(final String id) {
        List<Work> ws = new ArrayList<Work>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from work where c_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            Work w = null;
            while (rs.next()) {
                w = new Work();
                w.setId(rs.getString("w_id"));
                w.SetTitle(rs.getString("w_title"));
                w.setContent(rs.getString("w_content"));
                w.setCodetype(rs.getString("w_codetype"));
                w.setReferto(rs.getString("w_referto"));
                w.setKeylines(rs.getString("w_keylines"));
                w.setTestcasein(rs.getString("w_testcasein"));
                w.setTestcaseout(rs.getString("w_testcaseout"));
                w.setLimcpu(rs.getInt("w_limcpu"));
                w.setLimmem(rs.getInt("w_limmem"));
                w.setDotest(rs.getInt("w_dotest"));
                w.setTime(rs.getLong("w_time"));
                w.setCid(rs.getString("c_id"));
                ws.add(w);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return ws;
    }
    
    
    public static List<Work> getAll() {
        List<Work> ws = new ArrayList<Work>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from work");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            Work w = null;
            while (rs.next()) {
                w = new Work();
                w.setId(rs.getString("w_id"));
                w.SetTitle(rs.getString("w_title"));
                w.setContent(rs.getString("w_content"));
                w.setCodetype(rs.getString("w_codetype"));
                w.setReferto(rs.getString("w_referto"));
                w.setKeylines(rs.getString("w_keylines"));
                w.setTestcasein(rs.getString("w_testcasein"));
                w.setTestcaseout(rs.getString("w_testcaseout"));
                w.setLimcpu(rs.getInt("w_limcpu"));
                w.setLimmem(rs.getInt("w_limmem"));
                w.setDotest(rs.getInt("w_dotest"));
                w.setTime(rs.getLong("w_time"));
                w.setCid(rs.getString("c_id"));
                ws.add(w);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return ws;
    }
    
    public static void updateTitle(final String title, final String id) {
        update("w_title", title, id);
    }
    
    public static void updateContent(final String content, final String id) {
        update("w_content", content, id);
    }
    
    public static void updateCodetype(final String codetype, final String id) {
        update("w_codetype", codetype, id);
    }
    
    public static void updateReferto(final String referto, final String id) {
        update("w_referto", referto, id);
    }
    
    public static void updateKeylines(final String keylines, final String id) {
        update("w_keylines", keylines, id);
    }
    
    public static void updateTestcasein(final String testcasein, final String id) {
        update("w_testcasein", testcasein, id);
    }
    
    public static void updateTestcaseout(final String testcaseout, final String id) {
        update("w_testcaseout", testcaseout, id);
    }
    
    public static void updateLimcpu(final int limcpu, final String id) {
        update("w_limcpu", limcpu, id);
    }
    
    public static void updateLimmem(final int limmem, final String id) {
        update("w_limmem", limmem, id);
    }
    
    public static void startDotest(final String id) {
        updateDotest(1, id);
    }
    
    public static void endDotest(final String id) {
        updateDotest(0, id);
    }
    
    private static void updateDotest(final int dotest, final String id) {
        update("w_dotest", dotest, id);
    }
    
    public static void updateTime(final long time, final String id) {
        update("w_time", time, id);
    }
    
    public static void delete(final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("delete from work where w_id=?");
            ps.setString(1, id);
            ps.execute();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }

    
    
    
    
    private static void update(final String field, final long text, final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            String op = "update work set " + field + "=? where w_id=?";
            PreparedStatement ps = conn.prepareStatement(op);
            ps.setLong(1, text);
            ps.setString(2, id);
            ps.execute();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    private static void update(final String field, final int text, final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            String op = "update work set " + field + "=? where w_id=?";
            PreparedStatement ps = conn.prepareStatement(op);
            ps.setInt(1, text);
            ps.setString(2, id);
            ps.execute();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    
    private static void update(final String field, final String text, final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            String op = "update work set " + field + "=? where w_id=?";
            PreparedStatement ps = conn.prepareStatement(op);
            ps.setString(1, text);
            ps.setString(2, id);
            ps.execute();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    
    /*
    public static void main(String[] s) {
        Work w;
        
        w = new Work("1","title","content","codetype","referto","keylines","testcase",1,1,1,"1");
        System.out.println(WorkDao.add(w));
        w = new Work("2","title","content","codetype","referto","keylines","testcase",1,1,1,"2");
        System.out.println(WorkDao.add(w));
        w = new Work("3","title","content","codetype","referto","keylines","testcase",1,1,1,"2");
        System.out.println(WorkDao.add(w));
        
        w = WorkDao.getById("1");
        System.out.println(w.getCid()+w.getId()+w.getCodetype()+w.getContent()+w.getKeylines()+w.getLimcpu()+w.getLimmem()+w.getReferto()+w.getTestcase()+w.getTime()+w.getTitle());
                
        List<Work> ws = WorkDao.getByCid("2");
        for(int i=0; i < ws.size(); i++) {
            w = ws.get(i);
            System.out.println(w.getCid()+w.getId()+w.getCodetype()+w.getContent()+w.getKeylines()+w.getLimcpu()+w.getLimmem()+w.getReferto()+w.getTestcase()+w.getTime()+w.getTitle());
        }
        
        List<Work> ws = WorkDao.getAll();
        for(int i=0; i < ws.size(); i++) {
            w = ws.get(i);
            System.out.println(w.getCid()+w.getId()+w.getCodetype()+w.getContent()+w.getKeylines()+w.getLimcpu()+w.getLimmem()+w.getReferto()+w.getTestcase()+w.getTime()+w.getTitle());
        }
        
        WorkDao.updateCodetype("new", "1");
        WorkDao.updateContent("new", "1");
        WorkDao.updateKeylines("new", "1");
        WorkDao.updateLimcpu(12, "1");
        WorkDao.updateLimmem(12, "1");
        WorkDao.updateReferto("new", "1");
        WorkDao.updateTestcase("new", "1");
        WorkDao.updateTime(123, "1");
        WorkDao.updateTitle("new", "1");
        
        WorkDao.delete("12");
        WorkDao.delete("1");
    }
    */
    
}
