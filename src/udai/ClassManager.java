package udai;

import java.util.ArrayList;
import java.util.List;

import dao.CSmapDao;
import dao.ClassDao;
import dao.StudentDao;
import dao.WorkDao;
import dataunit.CClass;
import dataunit.CSmap;
import dataunit.Student;
import dataunit.Work;
import interactive.Global;

public class ClassManager {
    
    public static List<CClass> getClasses(String tid) {
        return ClassDao.getByTid(tid);
    }
    
    public static CClass getClass(String cid) {
        return ClassDao.getById(cid);
    }
    
    public static List<Student> getStudents(String cid) {
        List<CSmap> csms = CSmapDao.getByCid(cid);
        List<Student> ss = new ArrayList<Student>();
        for (int i=0; i < csms.size(); i++) {
            ss.add(StudentDao.get(csms.get(i).getSid()));
        }
        return ss;
    }
    
    public static List<Work> getWorks(String cid) {
        return WorkDao.getByCid(cid);
    }

    public static char addClass(String id, String name, String resume, String tid) {
        CClass c = new CClass(id, name, resume, tid);
        boolean ret = ClassDao.add(c);
        if (!ret)
            return Global.FAILURE;
        return Global.SUCCESS;
    }
    
    public static char deleteClass(String id) {
        ClassDao.delete(id);
        return Global.SUCCESS;
    }
    
    public static char updateClassName(String id, String name) {
        ClassDao.updateName(name, id);
        return Global.SUCCESS;
    }
    
    public static char updateClassResume(String id, String resume) {
        ClassDao.updateResume(resume, id);
        return Global.SUCCESS;
    }
    
    public static char addStudent(String cid, String sid) {
        CSmap csm = new CSmap(cid, sid);
        boolean ret = CSmapDao.add(csm);
        if (!ret)
            return Global.FAILURE;
        return Global.SUCCESS;
    }
    
    public static char deleteStudent(String cid, String sid) {
        CSmapDao.delete(cid, sid);
        return Global.SUCCESS;
    }
}
