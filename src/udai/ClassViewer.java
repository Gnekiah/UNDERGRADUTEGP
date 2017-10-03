package udai;

import java.util.ArrayList;
import java.util.List;

import dao.CSmapDao;
import dao.ClassDao;
import dao.ReplyDao;
import dao.WorkDao;
import dataunit.CClass;
import dataunit.CSmap;
import dataunit.Reply;
import dataunit.Work;
import interactive.Global;

public class ClassViewer {
    
    public static CClass getClass(String cid) {
        return ClassDao.getById(cid);
    }
    
    public static List<CClass> getClasses(String sid) {
        List<CSmap> csms = CSmapDao.getBySid(sid);
        List<CClass> ccs = new ArrayList<CClass>();
        for(int i=0; i < csms.size(); i++) {
            ccs.add(ClassDao.getById(csms.get(i).getCid()));
        }
        return ccs;
    }
    
    public static List<Work> getWorks(String cid) {
        return WorkDao.getByCid(cid);
    }
    
    public static Work getWork(String wid) {
        return WorkDao.getById(wid);
    }
    
    public static char addReply(String id, String source, int staticscore, int dynamicscore, 
            String staticreport, String dynamicreport, long time, String s_id, String w_id) {
        boolean ret = ReplyDao.add(new Reply(id, source, staticscore, dynamicscore, staticreport, dynamicreport, time, s_id, w_id));
        if (ret) {
            return Global.SUCCESS;
        }
        return Global.FAILURE;
    }
   
}
