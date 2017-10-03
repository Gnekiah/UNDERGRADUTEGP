package demo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dataunit.Teacher;

public class JsonDemo {
    public String s1 = null;
    public List<Teacher> t = null;
    
    
    public static void main(String[] s) {
        List<Teacher> ts = new ArrayList<Teacher>();
        ts.add(new Teacher("myid", "myshadow", "mysalt", "myname", "myemail", "myresume"));
        ts.add(new Teacher("myid2", "myshadow2", "mysalt2", "myname2", "myemail2", "myresume2"));
        Gson g = new Gson();
        /*
        Teacher t = new Teacher();
        t.setId("teacher");
        t.setEmail("root@mail.com");
        t.setName("Teacher name");
        t.setResume("hello, this is resume");
        t.setSalt("this is salt");
        t.setShadow("this is sshadow");
        */
        
        JsonDemo jd = new JsonDemo();
        jd.s1 = "this is a id";
        jd.t = ts;
        String ss = g.toJson(jd);
        System.out.println(ss);
    }

}
