package jsonunit;

import java.util.ArrayList;
import java.util.List;

import dataunit.CClass;
import dataunit.Work;
import udai.CommonOps;

public class ClassViewJson {
    public String id = null;
    public String name = null;
    public String resume = null;
    public List<CellWorkForClassViewJson> works = null;
    
    public ClassViewJson(CClass c, List<Work> ws) {
        this.id = c.getId();
        this.name = c.getName();
        this.resume = c.getResume();
        works = new ArrayList<CellWorkForClassViewJson>();
        for (Work w : ws) {
            works.add(new CellWorkForClassViewJson(w.getId(), w.getTitle(), CommonOps.timestamp2Date(w.getTime())));
        }
    }

}
