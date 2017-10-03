package dynio;

public class Testunit {
    
    private String wid = null;
    private String sid = null;
    private String type = null;
    private String workpath = null;
    private String studentpath = null;
    private String srcpath = null;
    private String inpath = null;
    private String outpath = null;
    private int cpu = 0;
    private int mem = 0;
    private int tracelevel = 0;
    private String report = null;
    
    
    Testunit() {
        
    }
    
    
    public String getWid() {
        return this.wid;
    }

    public String getSid() { 
        return this.sid;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getWorkpath() {
        return this.workpath;
    }
    
    public String getStudentpath() {
        return this.studentpath;
    }
    
    public String getSrcpath() {
        return this.srcpath;
    }
    
    public String getInpath() {
        return this.inpath;
    }
    
    public String getOutpath() {
        return this.outpath;
    }
    
    public int getCpu() {
        return this.cpu;
    }
    
    public int getMem() {
        return this.mem;
    }
    
    public int getTracelevel() {
        return this.tracelevel;
    }
    
    public String getReport() {
        return this.report;
    }
    
    public void setWid(String wid) {
        this.wid = wid;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setWorkpath(String workpath) {
        this.workpath = workpath;
    }
    
    public void setStudentpath(String studentpath) {
        this.studentpath = studentpath;
    }
    
    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }
    
    public void setInpath(String inpath) {
        this.inpath = inpath;
    }
     
    public void setOutpath(String outpath) {
        this.outpath = outpath;
    }
    
    public void setCpu(int cpu) {
        this.cpu = cpu;
    }
    
    public void setMem(int mem) {
        this.mem = mem;
    }
    
    public void setTracelevel(int tracelevel) {
        this.tracelevel = tracelevel;
    }
    
    public void setReport(String report) {
        this.report = report;
    }
}
