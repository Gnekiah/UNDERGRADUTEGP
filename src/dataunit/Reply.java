package dataunit;

public class Reply {
    private String id;
    private String source;
    private int staticscore;
    private int dynamicscore;
    private String staticreport;
    private String dynamicreport;
    private long time;
    private String s_id;
    private String w_id;
    
    public Reply() {
    }
    
    public Reply(String id, String source, int staticscore, int dynamicscore, 
            String staticreport, String dynamicreport, long time, String s_id, String w_id) {
        this.id = id;
        this.source = source;
        this.staticscore = staticscore;
        this.dynamicscore = dynamicscore;
        this.staticreport = staticreport;
        this.dynamicreport = dynamicreport;
        this.time = time;
        this.s_id = s_id;
        this.w_id = w_id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public int getStaticscore() {
        return this.staticscore;
    }
    
    public int getDynamicscore() {
        return this.dynamicscore;
    }
    
    public String getStaticreport() {
        return this.staticreport;
    }
    
    public String getDynamicreport() {
        return this.dynamicreport;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public String getSid() {
        return this.s_id;
    }
    
    public String getWid() {
        return this.w_id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public void setStaticscore(int staticscore) {
        this.staticscore = staticscore;
    }
    
    public void setDynamicscore(int dynamicscore) {
        this.dynamicscore = dynamicscore;
    }
    
    public void setStaticreport(String staticreport) {
        this.staticreport = staticreport;
    }
    
    public void setDynamicreport(String dynamicreport) {
        this.dynamicreport = dynamicreport;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public void setSid(String s_id) {
        this.s_id = s_id;
    }
    
    public void SetWid(String w_id) {
        this.w_id = w_id;
    }
    
}
