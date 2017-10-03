package jsonunit;

import java.util.ArrayList;
import java.util.List;

import dao.ReplyDao;
import dataunit.Reply;
import dataunit.Student;
import interactive.Global;
import udai.CommonOps;
import udai.FileOps;

public class DoTestJson {
    List<CellForDoTestJson> dotests = null;

    public DoTestJson(List<Student> students, String wid) {
        dotests = new ArrayList<CellForDoTestJson>();
        String sid = null;
        Reply r = null;
        for (Student s : students) {
            sid = s.getId();
            r = ReplyDao.getByWS(wid, sid);
            if (r == null) {
                dotests.add(new CellForDoTestJson(sid, 0, 0, "no", "", "", ""));
                continue;
            }
            int ss = r.getStaticscore();
            int ds = r.getDynamicscore();
            String sr = r.getStaticreport().equals("null") ? "" : FileOps.loadFile(Global.STATIC_REPORT_DIR + wid + "/" + sid);
            String dr = r.getDynamicreport().equals("null") ? "" : FileOps.loadFile(Global.DYNAMIC_REPORT_DIR + wid + "/" + sid);
            String time = CommonOps.timestamp2Date(r.getTime()); 
            dotests.add(new CellForDoTestJson(sid, ss, ds, (ss==0&&ds==0 ? "no" : "yes"), sr, dr, time));
        }
    }
}
