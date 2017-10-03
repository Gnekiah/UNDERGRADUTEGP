package jsonunit;

public class CellForDoTestJson {
    String sid = null;
    int staticScore = 0;
    int dynamicScore = 0;
    String tested = null;
    String staticReport = null;
    String dynamicReport = null;
    String time = null;
    
    public CellForDoTestJson(String sid, int ss, int ds, String ted, String sr, String dr, String time) {
        this.sid = sid;
        this.staticScore = ss;
        this.dynamicScore = ds;
        this.tested = ted;
        this.staticReport = sr;
        this.dynamicReport = dr;
        this.time = time;
    }
}
