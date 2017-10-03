package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataunit.CSmap;
import interactive.Global;

public class CSmapDao {
    public static boolean add(final CSmap csm) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into csmap values(?,?)");
            ps.setString(1, csm.getSid());
            ps.setString(2, csm.getCid());
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

    public static List<CSmap> getBySid(final String sid) {
        List<CSmap> csms = new ArrayList<CSmap>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from csmap where s_id=?");
            ps.setString(1, sid);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                CSmap csm = new CSmap();
                csm.setCid(rs.getString("c_id"));
                csm.setSid(rs.getString("s_id"));
                csms.add(csm);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return csms;
    }
    
    public static List<CSmap> getByCid(final String cid) {
        List<CSmap> csms = new ArrayList<CSmap>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from csmap where c_id=?");
            ps.setString(1, cid);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                CSmap csm = new CSmap();
                csm.setCid(rs.getString("c_id"));
                csm.setSid(rs.getString("s_id"));
                csms.add(csm);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return csms;
    }
    
    
    public static void delete(final String cid, final String sid) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("delete from csmap where c_id=? and s_id=?");
            ps.setString(1, cid);
            ps.setString(2, sid);
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
        CSmap csm;
        
        csm = new CSmap("2", "1");
        System.out.println(CSmapDao.add(csm));
        
        List<CSmap> csms = CSmapDao.getByCid("1");
        for (int i=0; i < csms.size(); i++) {
            csm = csms.get(i);
            System.out.println(csm.getCid()+csm.getSid());
        }
        csms = CSmapDao.getBySid("2");
        for (int i=0; i < csms.size(); i++) {
            csm = csms.get(i);
            System.out.println(csm.getCid()+csm.getSid());
        }  
    }
    */
}
