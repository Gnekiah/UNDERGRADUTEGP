package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import dataunit.Teacher;
import interactive.Global;
import udai.Verification;

/**
 * 
 * @author Ekira
 *
 */
public class DaoConfig {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(Global.DRIVER_CLASS);
            conn = DriverManager.getConnection(Global.JDBC_URL, Global.DB_USER, Global.DB_PASSWD);
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            createDB();
            return null;
        }
        return conn;        
    }
   
    
    /**
     * create a database and some tables
     */
    private static void createDB() {
        Connection conn = null;
        try {
            Class.forName(Global.DRIVER_CLASS);
            conn = DriverManager.getConnection(Global.DB_URL, Global.DB_USER, Global.DB_PASSWD);
            conn.createStatement().executeUpdate("CREATE DATABASE gp");
            conn.close();
            conn = DriverManager.getConnection(Global.JDBC_URL, Global.DB_USER, Global.DB_PASSWD);
            conn.createStatement().executeUpdate(Global.CR_TEACHER);
            conn.createStatement().executeUpdate(Global.CR_CLASS);
            conn.createStatement().executeUpdate(Global.CR_STUDENT);
            conn.createStatement().executeUpdate(Global.CR_CSMAP);
            conn.createStatement().executeUpdate(Global.CR_WORK);
            conn.createStatement().executeUpdate(Global.CR_REPLY);
            conn.close();
            String salt = Verification.saltGen();
            String shadow = Verification.shadowGen(Global.ROOT_ID, Global.ROOT_PASSWD, salt);
            TeacherDao.add(new Teacher(Global.ROOT_ID, shadow, salt, "", "", ""));
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
    }
    
    /*
    public static void main(String[] s) {
        DaoConfig.createDB();
    }
    */
    
}
