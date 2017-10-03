package dataunit;

/**
 * 
 * @author Ekira
 *
 */
public class Teacher {
    /**
     * id could not be changed after initialized
     */
    private String id;
    private String shadow;
    private String salt;
    private String name;
    private String email;
    private String resume;
    
    public Teacher() {
    }
    
    public Teacher(String id, String shadow, String salt, String name, String email, String resume) {
        this.id = id;
        this.shadow = shadow;
        this.salt = salt;
        this.name = name;
        this.email = email;
        this.resume = resume;
    }
    
    public String getId() {
        return id;
    }
    
    public String getShadow() {
        return shadow;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    public String getResume() {
        return resume;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setShadow(String shadow) {
        this.shadow = shadow;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setResume(String resume) {
        this.resume = resume;
    }
}
