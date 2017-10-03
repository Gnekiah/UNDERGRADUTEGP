package dataunit;

public class CClass {
    private String id;
    private String name;
    private String resume;
    private String t_id;
    
    public CClass() {
    }
    
    public CClass(String id, String name, String resume, String t_id) {
        this.id = id;
        this.name = name;
        this.resume = resume;
        this.t_id = t_id;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getResume() {
        return resume;
    }
    
    public String getTid() {
        return t_id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setResume(String resume) {
        this.resume = resume;
    }
    
    public void setTid(String t_id) {
        this.t_id = t_id;
    }
}
