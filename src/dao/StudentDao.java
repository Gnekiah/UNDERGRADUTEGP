package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataunit.Student;
import interactive.Global;

public class StudentDao {
    
    
    public static Boolean add(final Student student) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into student values(?,?,?,?,?,?)");
            ps.setString(1, student.getId());
            ps.setString(2, student.getShadow());
            ps.setString(3, student.getSalt());
            ps.setString(4, student.getName());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getResume());
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
    public static Student get(final String id) {
        Connection conn = DaoConfig.getConnection();
        Student student = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student where s_id=?");
            ps.setString(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getString("s_id"));
                student.setShadow(rs.getString("s_shadow"));
                student.setSalt(rs.getString("s_salt"));
                student.setName(rs.getString("s_name"));
                student.setEmail(rs.getString("s_email"));
                student.setResume(rs.getString("s_resume"));
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return student;
    }
    
    /**
     * 
     * @return
     */
    public static List<Student> getAll() {
        List<Student> students = new ArrayList<Student>();
        Connection conn = DaoConfig.getConnection();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from student");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("s_id"));
                student.setShadow(rs.getString("s_shadow"));
                student.setSalt(rs.getString("s_salt"));
                student.setName(rs.getString("s_name"));
                student.setEmail(rs.getString("s_email"));
                student.setResume(rs.getString("s_resume"));
                students.add(student);
            }
            conn.close();
        } catch (SQLException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return students;
    }
    

    public static void updateShadow(final String shadow, final String id) {
        update("s_shadow", shadow, id);
    }
    

    public static void updateSalt(final String salt, final String id) {
        update("s_salt", salt, id);
    }
    

    public static void updateName(final String name, final String id) {
        update("s_name", name, id);
    }
    

    public static void updateEmail(final String email, final String id) {
        update("s_email", email, id);
    }
    

    public static void updateResume(final String resume, final String id) {
        update("s_resume", resume, id);
    }


    public static void delete(final String id) {
        Connection conn = DaoConfig.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps=conn.prepareStatement("delete from student where s_id=?");
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
            String op = "update student set " + field + "=? where s_id=?";
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
        Student student;

        student = new Student("1", "shadow1", "salt1", "name1", "email1", "resume1");
        System.out.println(StudentDao.add(student));
        student = new Student("2", "shadow2", "salt2", "name2", "email2", "resume2");
        System.out.println(StudentDao.add(student));
        student = new Student("2", "shadow2", "salt2", "name2", "email2", "resume2");
        System.out.println(StudentDao.add(student));

        student = StudentDao.get("0");
        if (student == null) {
            System.out.println("teacher == null");
        }
        else {
            System.out.println(student.getId()+student.getEmail()+student.getName()+student.getResume()+student.getSalt()+student.getShadow());
        }
        student = StudentDao.get("1");
        if (student == null) {
            System.out.println("teacher == null");
        }
        else {
            System.out.println(student.getId()+student.getEmail()+student.getName()+student.getResume()+student.getSalt()+student.getShadow());
        }

        List<Student> students = StudentDao.getAll();
        for (int i=0; i < students.size(); i++) {
            student = students.get(i);
            System.out.println(student.getId()+student.getEmail()+student.getName()+student.getResume()+student.getSalt()+student.getShadow());
        }       

        StudentDao.updateShadow("shadowupdate1", "1");
        StudentDao.updateSalt("saleupdate1", "1");
        StudentDao.updateName("nameupdate1", "1");
        StudentDao.updateResume("resumeupdate1", "1");
        StudentDao.updateEmail("emailupdate1", "1");
  
        StudentDao.delete("2");
    }
    */

}
