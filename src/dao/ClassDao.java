package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataunit.CClass;
import interactive.Global;

public class ClassDao {

    public static boolean add(final CClass c) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into class values(?,?,?,?)");
            ps.setString(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getResume());
            ps.setString(4, c.getTid());
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
    
    
    public static CClass getById(final String id) {
        Connection conn = DaoConfig.getConnection();
        CClass c = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from class where c_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                c = new CClass();
                c.setId(rs.getString("c_id"));
                c.setName(rs.getString("c_name"));
                c.setResume(rs.getString("c_resume"));
                c.setTid(rs.getString("t_id"));
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return c;
    }
    
    
    public static List<CClass> getByTid(final String tid) {
        List<CClass> cs = new ArrayList<CClass>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from class where t_id=?");
            ps.setString(1, tid);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                CClass c = new CClass();
                c.setId(rs.getString("c_id"));
                c.setName(rs.getString("c_name"));
                c.setResume(rs.getString("c_resume"));
                c.setTid(rs.getString("t_id"));
                cs.add(c);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return cs;
    }
    
    
    public static List<CClass> getAll() {
        List<CClass> cs = new ArrayList<CClass>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from class");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                CClass c = new CClass();
                c.setId(rs.getString("c_id"));
                c.setName(rs.getString("c_name"));
                c.setResume(rs.getString("c_resume"));
                c.setTid(rs.getString("t_id"));
                cs.add(c);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return cs;
    }
    
    
    public static void updateName(final String name, final String id) {
        update("c_name", name, id);
    }
    
    public static void updateResume(final String resume, final String id) {
        update("c_resume", resume, id);
    }
    
    
    public static void delete(final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("delete from class where c_id=?");
            ps.setString(1, id);
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
            String op = "update class set " + field + "=? where c_id=?";
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
    public static void main(String s[]) {
        CClass c;
        
        c = new CClass("1", "class1", "resume1", "1");
        System.out.println(ClassDao.add(c));
        c = new CClass("2", "class2", "resume2", "1");
        System.out.println(ClassDao.add(c));
        c = new CClass("2", "class2", "resume2", "1");
        System.out.println(ClassDao.add(c));
        
        c = ClassDao.getById("1");
        System.out.println(c.getId()+c.getName()+c.getResume()+c.getTid());
        List<CClass> cs;
        cs = ClassDao.getByTid("1");
        for(int i=0; i < cs.size(); i++) {
            c = cs.get(i);
            System.out.println(c.getId()+c.getName()+c.getResume()+c.getTid());
        }
        cs = ClassDao.getAll();
        for(int i=0; i < cs.size(); i++) {
            c = cs.get(i);
            System.out.println(c.getId()+c.getName()+c.getResume()+c.getTid());
        }
        
        ClassDao.updateName("newname1", "1");
        ClassDao.updateName("newname1", "3");
        ClassDao.updateResume("newresume1", "2");
        ClassDao.updateResume("newresume1", "3");
        
        ClassDao.delete("1");
        ClassDao.delete("11");
    }
    */
}
