package jsonunit;

import java.io.File;

import dataunit.Work;
import interactive.Global;
import udai.CommonOps;
import udai.FileOps;

public class WorkJson {
    String id = null;
    String title = null;
    String content = null;
    String codetype = null;
    String referto = null;
    String keylines = null;
    String testcasein = null;
    String testcaseout = null;
    int cpu = 0;
    int mem = 0;
    String time = null;
            
    public WorkJson(Work w) {
        id = w.getId();
        title = w.getTitle();
        // code type
        codetype = w.getCodetype();
        if (codetype.equals("null"))
            codetype = "";
        // key lines
        keylines = w.getKeylines();
        if (keylines.equals("null"))
            keylines = "";
        // content
        content = w.getContent();
        if (content.equals("null")) {
            content = "";
        } else {
            content = doLoadFile(Global.CONTENT_DIR + id);
        }
        // referto
        referto = w.getReferto();
        if (referto.equals("null")) {
            referto = "";
        } else {
            referto = doLoadFile(Global.REFERTO_DIR + id);
        }
        // testcase in
        testcasein = w.getTestcasein();
        if (testcasein.equals("null")) {
            testcasein = "";
        } else {
            testcasein = doLoadFile(Global.TESTCASEIN_DIR + id);
        }
        // testcase out
        testcaseout = w.getTestcaseout();
        if (testcaseout.equals("null")) {
            testcaseout = "";
        } else {
            testcaseout = doLoadFile(Global.TESTCASEOUT_DIR + id);
        }
        // limit
        cpu = w.getLimcpu();
        mem = w.getLimmem();
        time = CommonOps.timestamp2Date(w.getTime());
    }
    
    private String doLoadFile(String path) {
        File f = new File(path);
        if (f.isFile()) {
            return FileOps.loadFile(f);
        }
        return "";
    }
    
}
