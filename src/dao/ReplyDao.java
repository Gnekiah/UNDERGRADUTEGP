package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataunit.Reply;
import interactive.Global;

public class ReplyDao {
    
    public static boolean add(final Reply r) {
        Connection conn = DaoConfig.getConnection();
        if (getByWS(r.getWid(), r.getSid()) != null) {
            return false;
        }
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into reply value(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, r.getId());
            ps.setString(2, r.getSource());
            ps.setInt(3, r.getStaticscore());
            ps.setInt(4, r.getDynamicscore());
            ps.setString(5, r.getStaticreport());
            ps.setString(6, r.getDynamicreport());
            ps.setLong(7, r.getTime());
            ps.setString(8, r.getSid());
            ps.setString(9, r.getWid());
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
    
    
    public static Reply getById(final String id) {
        Connection conn = DaoConfig.getConnection();
        Reply r = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reply where r_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                r = new Reply();
                r.setDynamicreport(rs.getString("r_dynamicreport"));
                r.setDynamicscore(rs.getInt("r_dynamicscore"));
                r.setId(rs.getString("r_id"));
                r.setSid(rs.getString("s_id"));
                r.setSource(rs.getString("r_source"));
                r.setStaticreport(rs.getString("r_staticreport"));
                r.setStaticscore(rs.getInt("r_staticscore"));
                r.setTime(rs.getLong("r_time"));
                r.SetWid(rs.getString("w_id"));
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return r;
    }

    
    public static List<Reply> getBySid(final String id) {
        List<Reply> rps = new ArrayList<Reply>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reply where s_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            Reply r = null;
            while (rs.next()) {
                r = new Reply();
                r.setDynamicreport(rs.getString("r_dynamicreport"));
                r.setDynamicscore(rs.getInt("r_dynamicscore"));
                r.setId(rs.getString("r_id"));
                r.setSid(rs.getString("s_id"));
                r.setSource(rs.getString("r_source"));
                r.setStaticreport(rs.getString("r_staticreport"));
                r.setStaticscore(rs.getInt("r_staticscore"));
                r.setTime(rs.getLong("r_time"));
                r.SetWid(rs.getString("w_id"));
                rps.add(r);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return rps;
    }
    
    
    public static List<Reply> getByWid(final String id) {
        List<Reply> rps = new ArrayList<Reply>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reply where w_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            Reply r = null;
            while (rs.next()) {
                r = new Reply();
                r.setDynamicreport(rs.getString("r_dynamicreport"));
                r.setDynamicscore(rs.getInt("r_dynamicscore"));
                r.setId(rs.getString("r_id"));
                r.setSid(rs.getString("s_id"));
                r.setSource(rs.getString("r_source"));
                r.setStaticreport(rs.getString("r_staticreport"));
                r.setStaticscore(rs.getInt("r_staticscore"));
                r.setTime(rs.getLong("r_time"));
                r.SetWid(rs.getString("w_id"));
                rps.add(r);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return rps;
    }
    
    public static Reply getByWS(final String wid, final String sid) {
        Reply r = null;
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reply where w_id=? and s_id=?");
            ps.setString(1, wid);
            ps.setString(2, sid);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                r = new Reply();
                r.setDynamicreport(rs.getString("r_dynamicreport"));
                r.setDynamicscore(rs.getInt("r_dynamicscore"));
                r.setId(rs.getString("r_id"));
                r.setSid(rs.getString("s_id"));
                r.setSource(rs.getString("r_source"));
                r.setStaticreport(rs.getString("r_staticreport"));
                r.setStaticscore(rs.getInt("r_staticscore"));
                r.setTime(rs.getLong("r_time"));
                r.SetWid(rs.getString("w_id"));
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return r;
    }
    
    public static List<Reply> getAll() {
        List<Reply> rps = new ArrayList<Reply>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reply");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            Reply r = null;
            while (rs.next()) {
                r = new Reply();
                r.setDynamicreport(rs.getString("r_dynamicreport"));
                r.setDynamicscore(rs.getInt("r_dynamicscore"));
                r.setId(rs.getString("r_id"));
                r.setSid(rs.getString("s_id"));
                r.setSource(rs.getString("r_source"));
                r.setStaticreport(rs.getString("r_staticreport"));
                r.setStaticscore(rs.getInt("r_staticscore"));
                r.setTime(rs.getLong("r_time"));
                r.SetWid(rs.getString("w_id"));
                rps.add(r);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return rps;
    }
    
    public static void updateSource(final String source, final String id) {
        update("r_source", source, id);
    }
    
    public static void updateStaticscore(final int staticscore, final String id) {
        update("r_staticscore", staticscore, id);
    }
    
    public static void updateDynamicscore(final int dynamicscore, final String id) {
        update("r_dynamicscore", dynamicscore, id);
    }
    
    public static void updateStaticreport(final String staticreport, final String id) {
        update("r_staticreport", staticreport, id);
    }
    
    public static void updateDynamicreport(final String dynamicreport, final String id) {
        update("r_dynamicreport", dynamicreport, id);
    }
    
    public static void updateTime(final long time, final String id) {
        update("r_time", time, id);
    }
    
    
    public static void delete(final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("delete from reply where r_id=?");
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
            String op = "update reply set " + field + "=? where r_id=?";
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
            String op = "update reply set " + field + "=? where r_id=?";
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
            String op = "update reply set " + field + "=? where r_id=?";
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
        Reply w;
        
        w = new Reply("1","/source-1",100,99,"sreport","dreport",12345678,"2","2");
        System.out.println(ReplyDao.add(w));
        w = new Reply("2","/source-1",100,99,"sreport","dreport",12345678,"3","3");
        System.out.println(ReplyDao.add(w));
        w = new Reply("2","/source-1",100,99,"sreport","dreport",12345678,"2","1");
        System.out.println(ReplyDao.add(w));
        w = new Reply("2","/source-1",100,99,"sreport","dreport",12345678,"1","2");
        System.out.println(ReplyDao.add(w));
        w = new Reply("3","/source-1",100,99,"sreport","dreport",12345678,"1","2");
        System.out.println(ReplyDao.add(w));
        
        w = ReplyDao.getById("5");
        System.out.println(w.getDynamicreport()+w.getDynamicscore()+w.getId()+w.getSid()+w.getSource()+w.getStaticreport()+w.getStaticscore()+w.getTime()+w.getWid());
        
        List<Reply> ws = ReplyDao.getBySid("2");
        for(int i=0; i < ws.size(); i++) {
            w = ws.get(i);
            System.out.println(w.getDynamicreport()+w.getDynamicscore()+w.getId()+w.getSid()+w.getSource()+w.getStaticreport()+w.getStaticscore()+w.getTime()+w.getWid());
        }
        
        List<Reply> ws = ReplyDao.getByWid("2");
        for(int i=0; i < ws.size(); i++) {
            w = ws.get(i);
            System.out.println(w.getDynamicreport()+w.getDynamicscore()+w.getId()+w.getSid()+w.getSource()+w.getStaticreport()+w.getStaticscore()+w.getTime()+w.getWid());
        }
        
        List<Reply> ws = ReplyDao.getAll();
        for(int i=0; i < ws.size(); i++) {
            w = ws.get(i);
            System.out.println(w.getDynamicreport()+w.getDynamicscore()+w.getId()+w.getSid()+w.getSource()+w.getStaticreport()+w.getStaticscore()+w.getTime()+w.getWid());
        }
        
        ReplyDao.updateDynamicreport("01drpt", "1");
        ReplyDao.updateDynamicscore(5, "1");
        ReplyDao.updateSource("02src", "1");
        ReplyDao.updateStaticreport("srpt", "1");
        ReplyDao.updateStaticscore(6, "1");
        ReplyDao.updateTime(12345, "1");
        
        ReplyDao.delete("12");
        ReplyDao.delete("1");
    }
    */
    
}
