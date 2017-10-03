package dataunit;

public class Work {
    private String id;
    private String title;
    private String content;
    private String codetype;
    private String referto;
    private String keylines;
    private String testcasein;
    private String testcaseout;
    private int limcpu;
    private int limmem;
    private int dotest;
    private long time;
    private String c_id;
    
    public Work() {
    }
    
    public Work(String id, String title, String content, String codetype, String referto,
            String keylines, String testcasein, String testcaseout, int limcpu, int limmem, long time, String c_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.codetype = codetype;
        this.referto = referto;
        this.keylines = keylines;
        this.testcasein = testcasein;
        this.testcaseout = testcaseout;
        this.limcpu = limcpu;
        this.limmem = limmem;
        this.dotest = 0;
        this.time = time;
        this.c_id = c_id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public String getCodetype() {
        return this.codetype;
    }
    
    public String getReferto() {
        return this.referto;
    }
    
    public String getKeylines() {
        return this.keylines;
    }
    
    public String getTestcasein() {
        return this.testcasein;
    }
    
    public String getTestcaseout() {
        return this.testcaseout;
    }
    
    public int getLimcpu() {
        return this.limcpu;
    }
    
    public int getLimmem() {
        return this.limmem;
    }
    
    public int getDotest() {
        return this.dotest;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public String getCid() {
        return this.c_id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void SetTitle(String title) {
        this.title = title;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setCodetype(String codetype) {
        this.codetype = codetype;
    }
    
    public void setReferto(String referto) {
        this.referto = referto;
    }
    
    public void setKeylines(String keylines) {
        this.keylines = keylines;
    }
    
    public void setTestcasein(String testcasein) {
        this.testcasein = testcasein;
    }
    
    public void setTestcaseout(String testcaseout) {
        this.testcaseout = testcaseout;
    }
    
    public void setLimcpu(int limcpu) {
        this.limcpu = limcpu;
    }
    
    public void setLimmem(int limmem) {
        this.limmem = limmem;
    }
    
    public void setDotest(int dotest) {
        this.dotest = dotest;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public void setCid(String c_id) {
        this.c_id = c_id;
    }

}
