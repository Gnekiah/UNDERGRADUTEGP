package jsonunit;

import java.util.ArrayList;
import java.util.List;

import dataunit.CClass;
import dataunit.Student;

public class StudentJson {
    String id = null;
    String name = null;
    String email = null;
    String resume = null;
    List<CellClassForStudentJson> classes = null;
    
    public StudentJson(Student s, List<CClass> cs) {
        classes = new ArrayList<CellClassForStudentJson>();
        for (CClass c : cs) {
            classes.add(new CellClassForStudentJson(c.getId(), c.getName(), c.getResume()));
        }
        this.id = s.getId();
        this.name = s.getName();
        this.email = s.getEmail();
        this.resume = s.getResume();
    }
    

}
