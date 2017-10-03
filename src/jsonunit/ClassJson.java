package jsonunit;

import java.util.ArrayList;
import java.util.List;

import dataunit.CClass;
import dataunit.Student;
import dataunit.Work;

public class ClassJson {
    public String id = null;
    public String name = null;
    public String resume = null;
    public List<CellWorkForClassJson> works = null;
    public List<CellStudentForClassJson> students = null;
    
    public ClassJson(CClass c, List<Work> ws, List<Student> ss) {
        this.id = c.getId();
        this.name = c.getName();
        this.resume = c.getResume();
        works = new ArrayList<CellWorkForClassJson>();
        students = new ArrayList<CellStudentForClassJson>();
        for (Work w : ws) {
            works.add(new CellWorkForClassJson(w.getId(), w.getTitle()));
        }
        for (Student s : ss) {
            students.add(new CellStudentForClassJson(s.getId(), s.getName()));
        }
    }

}
