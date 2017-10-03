package jsonunit;

import java.io.File;

import dataunit.Work;
import interactive.Global;
import udai.CommonOps;
import udai.FileOps;

public class WorkViewJson {
    String id = null;
    String title = null;
    String content = null;
    String codetype = null;
    String time = null;
            
    public WorkViewJson(Work w) {
        id = w.getId();
        title = w.getTitle();
        // code type
        codetype = w.getCodetype();
        if (codetype.equals("null"))
            codetype = "";
        // content
        content = w.getContent();
        if (content.equals("null")) {
            content = "";
        } else {
            content = doLoadFile(Global.CONTENT_DIR + id);
        }
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
