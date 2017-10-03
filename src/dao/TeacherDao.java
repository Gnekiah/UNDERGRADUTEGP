package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataunit.Teacher;
import interactive.Global;

/**
 * 
 * @author Ekira
 *
 */
public class TeacherDao {
    
    /**
     * 
     * @param teacher
     * @return
     */
    public static Boolean add(final Teacher teacher) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into teacher values(?,?,?,?,?,?)");
            ps.setString(1,  teacher.getId());
            ps.setString(2, teacher.getShadow());
            ps.setString(3, teacher.getSalt());
            ps.setString(4, teacher.getName());
            ps.setString(5, teacher.getEmail());
            ps.setString(6, teacher.getResume());
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
    
    /**
     * 
     * @param id
     * @return
     */
    public static Teacher get(final String id) {
        Connection conn = DaoConfig.getConnection();
        Teacher teacher = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from teacher where t_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getString("t_id"));
                teacher.setShadow(rs.getString("t_shadow"));
                teacher.setSalt(rs.getString("t_salt"));
                teacher.setName(rs.getString("t_name"));
                teacher.setEmail(rs.getString("t_email"));
                teacher.setResume(rs.getString("t_resume"));
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return teacher;
    }
    
    /**
     * 
     * @return
     */
    public static List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from teacher");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString("t_id"));
                teacher.setShadow(rs.getString("t_shadow"));
                teacher.setSalt(rs.getString("t_salt"));
                teacher.setName(rs.getString("t_name"));
                teacher.setEmail(rs.getString("t_email"));
                teacher.setResume(rs.getString("t_resume"));
                teachers.add(teacher);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return teachers;
    }
    
 
    public static void updateShadow(final String shadow, final String id) {
        update("t_shadow", shadow, id);
    }
    
  
    public static void updateSalt(final String salt, final String id) {
        update("t_salt", salt, id);
    }
    
 
    public static void updateName(final String name, final String id) {
        update("t_name", name, id);
    }
    
   
    public static void updateEmail(final String email, final String id) {
        update("t_email", email, id);
    }
    
  
    public static void updateResume(final String resume, final String id) {
        update("t_resume", resume, id);
    }


    public static void delete(final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps=conn.prepareStatement("delete from teacher where t_id=?");
            ps.setString(1, id);
            ps.execute();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    

    private static void update(final String field, final String str, final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            String op = "update teacher set " + field + "=? where t_id=?";
            PreparedStatement ps=conn.prepareStatement(op);
            ps.setString(1, str);
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
        Teacher teacher;
        
        teacher = new Teacher("1", "shadow1", "salt1", "name1", "email1", "resume1");
        System.out.println(TeacherDao.add(teacher));
        teacher = new Teacher("2", "shadow2", "salt2", "name2", "email2", "resume2");
        System.out.println(TeacherDao.add(teacher));
        teacher = new Teacher("2", "shadow2", "salt2", "name2", "email2", "resume2");
        System.out.println(TeacherDao.add(teacher));
        
        teacher = TeacherDao.get("0");
        if (teacher == null) {
            System.out.println("teacher == null");
        }
        else {
            System.out.println(teacher.getId()+teacher.getEmail()+teacher.getName()+teacher.getResume()+teacher.getSalt()+teacher.getShadow());
        }
        teacher = TeacherDao.get("1");
        if (teacher == null) {
            System.out.println("teacher == null");
        }
        else {
            System.out.println(teacher.getId()+teacher.getEmail()+teacher.getName()+teacher.getResume()+teacher.getSalt()+teacher.getShadow());
        }
        
        List<Teacher> teachers = TeacherDao.getAll();
        for (int i=0; i < teachers.size(); i++) {
            teacher = teachers.get(i);
            System.out.println(teacher.getId()+teacher.getEmail()+teacher.getName()+teacher.getResume()+teacher.getSalt()+teacher.getShadow());
        }
        
        TeacherDao.updateShadow("shadowupdate1", "1");
        TeacherDao.updateSalt("saleupdate1", "1");
        TeacherDao.updateName("nameupdate1", "1");
        TeacherDao.updateResume("resumeupdate1", "1");
        TeacherDao.updateEmail("emailupdate1", "1");
    
        TeacherDao.delete("2");
        
    }
    */
}
