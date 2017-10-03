package dataunit;

public class CSmap {
    private String c_id;
    private String s_id;
    
    public CSmap() {
    }
    
    public CSmap(String c_id, String s_id) {
        this.c_id = c_id;
        this.s_id = s_id;
    }
    
    public String getCid() {
        return c_id;
    }
    
    public String getSid() {
        return s_id;
    }
    
    public void setCid(String c_id) {
        this.c_id = c_id;
    }
    
    public void setSid(String s_id) {
        this.s_id = s_id;
    }

}
