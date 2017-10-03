package jsonunit;

import java.util.ArrayList;
import java.util.List;

import dataunit.CClass;
import dataunit.Teacher;

public class TeacherJson {
    String id = null;
    String name = null;
    String email = null;
    String resume = null;
    List<CellClassForTeacherJson> classes = null;
    
    public TeacherJson(Teacher t, List<CClass> cs) {
        classes = new ArrayList<CellClassForTeacherJson>();
        for (CClass c : cs) {
            classes.add(new CellClassForTeacherJson(c.getId(), c.getName(), c.getResume()));
        }
        this.id = t.getId();
        this.name = t.getName();
        this.email = t.getEmail();
        this.resume = t.getResume();
    }
    

}
