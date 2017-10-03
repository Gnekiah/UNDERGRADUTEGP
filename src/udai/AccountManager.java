package udai;

import java.util.List;

import dao.StudentDao;
import dao.TeacherDao;
import dataunit.Student;
import dataunit.Teacher;
import interactive.Global;

public class AccountManager {

    public static char registerTeacher(String id, String passwd, String op_id) {
        if (!op_id.equals(Global.ROOT_ID))
            return Global.PERMISSION_DENIED;
        char ret = idLegalityCheck(id);
        if (ret == Global.ID_ILLEGAL)
            return Global.ID_ILLEGAL;
        ret = passwdLegalityCheck(passwd);
        if (ret == Global.PASSWD_ILLEGAL) 
            return Global.PASSWD_ILLEGAL;
        String salt = Verification.saltGen();
        String shadow = Verification.shadowGen(id, passwd, salt);
        Teacher t = new Teacher(id, shadow, salt, "", "", "");
        if (TeacherDao.add(t))
            return Global.SUCCESS;
        return Global.FAILURE;
    }
    
    public static char registerStudent(String id, String passwd, String op_id) {
        if (Verification.accountType(op_id) != Global.TEACHER)
            return Global.PERMISSION_DENIED;
        char ret = idLegalityCheck(id);
        if (ret == Global.ID_ILLEGAL || !id.matches("[0-9]+"))
            return Global.ID_ILLEGAL;
        ret = passwdLegalityCheck(passwd);
        if (ret == Global.PASSWD_ILLEGAL) 
            return Global.PASSWD_ILLEGAL;
        String salt = Verification.saltGen();
        String shadow = Verification.shadowGen(id, passwd, salt);
        Student s = new Student(id, shadow, salt, "", "", "");
        if (StudentDao.add(s))
            return Global.SUCCESS;
        return Global.FAILURE;
    }
    
    public static char unregisterTeacher(String id, String op_id) {
        if (!op_id.equals(Global.ROOT_ID))
            return Global.PERMISSION_DENIED;
        if (id.equals(op_id))
            return Global.OPERATION_ILLEGAL;
        switch(Verification.accountType(id)) {
        case Global.TEACHER:
            TeacherDao.delete(id);
            return Global.SUCCESS;
        case Global.STUDENT:
            StudentDao.delete(id);
            return Global.SUCCESS;
        case Global.USER_NOT_EXIST:
            return Global.USER_NOT_EXIST;
        }
        return Global.FAILURE;
    }
    
    public static char unregisterStudent(String id, String op_id) {
        if (Verification.accountType(op_id) != Global.TEACHER)
            return Global.PERMISSION_DENIED;
        if (Verification.accountType(id) == Global.USER_NOT_EXIST)
            return Global.USER_NOT_EXIST;
        StudentDao.delete(id);
        return Global.SUCCESS;
    }
    
    
    public static Teacher getTeacher(String id) {
        return TeacherDao.get(id);
    }
    
    public static List<Teacher> getTeachers() {
        return TeacherDao.getAll();
    }
    
    public static Student getStudent(String id) {
        return StudentDao.get(id);
    }
    
    public static List<Student> getStudents() {
        return StudentDao.getAll();
    }
    
    public static char updateTeacherPasswd(String id, String passwd) {
        String salt = Verification.saltGen();
        TeacherDao.updateShadow(Verification.shadowGen(id, passwd, salt), id);
        TeacherDao.updateSalt(salt, id);
        return Global.SUCCESS;
    }
    
    public static char updateTeacherName(String id, String name) {
        TeacherDao.updateName(name, id);
        return Global.SUCCESS;
    }
    
    public static char updateTeacherEmail(String id, String email) {
        TeacherDao.updateEmail(email, id);
        return Global.SUCCESS;
    }
    
    public static char updateTeacherResume(String id, String resume) {
        TeacherDao.updateResume(resume, id);
        return Global.SUCCESS;
    }
    
    public static char updateStudentPasswd(String id, String passwd) {
        String salt = Verification.saltGen();
        StudentDao.updateShadow(Verification.shadowGen(id, passwd, salt), id);
        StudentDao.updateSalt(salt, id);
        return Global.SUCCESS;
    }
    
    public static char updateStudentName(String id, String name) {
        StudentDao.updateName(name, id);
        return Global.SUCCESS;
    }
    
    public static char updateStudentEmail(String id, String email) {
        StudentDao.updateEmail(email, id);
        return Global.SUCCESS;
    }
    
    public static char updateStudentResume(String id, String resume) {
        StudentDao.updateResume(resume, id);
        return Global.SUCCESS;
    }
    
    public static char passwdLegalityCheck(String passwd) {
        if (passwd.length() < 6 || passwd.length() > 20) {
            return Global.PASSWD_ILLEGAL;
        }
        return Global.SUCCESS;
    }
    
    private static char idLegalityCheck(String id) {
        if (id.length() > 8 || id.length() < 5) {
            return Global.ID_ILLEGAL;
        }
        return Global.SUCCESS;
    }
}
