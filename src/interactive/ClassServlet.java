package interactive;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dataunit.CClass;
import dataunit.Student;
import dataunit.Work;
import jsonunit.ClassJson;
import udai.ClassManager;
import udai.Verification;

@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {
    
    private static final long serialVersionUID = 4492976717355141274L;

    public ClassServlet() {
        
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.TEACHER) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        // request area
        // class id
        String classid = req.getParameter("classid");
        // return class info according classid
        // include class works and students
        String classreq = req.getParameter("classreq");
        // update class name
        String updatecname = req.getParameter("updatecname");
        // update class resume
        String updatecresume = req.getParameter("updatecresume");
        // delete a student
        String delstudent = req.getParameter("delstudent");
        // delete a class
        String deleteclass = req.getParameter("deleteclass");
        String result = "";
        
        // save class id
        if (classid != null) {
            boolean flag = true;
            for (Cookie c : cookies) {
                if (c.getName().equals("classid")) {
                    c.setValue(classid);
                    c.setMaxAge(Global.COOKIE_LIVING);
                    resp.addCookie(c);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Cookie c = new Cookie("classid", classid);
                c.setMaxAge(Global.COOKIE_LIVING);
                resp.addCookie(c);
            }
            resp.sendRedirect("/subsys_interactive/class.jsp");
            return;
        }
        // get class id 
        for (Cookie c : cookies) {
            if (c.getName().equals("classid")) {
                classid = c.getValue();
                break;
            }
        }
        // get class info and works and students
        if (classreq != null && classreq.equals("true")) {
            if (classid == null) {
                resp.sendRedirect("/subsys_interactive/teacher.jsp");
                return;
            }
            CClass c = ClassManager.getClass(classid);
            List<Work> ws = ClassManager.getWorks(classid);
            List<Student> ss = ClassManager.getStudents(classid);
            ClassJson cj = new ClassJson(c, ws, ss);
            result = new Gson().toJson(cj);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(result);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        // update class info
        // warning: while update class info, client must send the class id
        if (updatecname != null || updatecresume != null) {
            if (updatecname != null) {
                ClassManager.updateClassName(classid, updatecname);
            }
            if (updatecresume != null) {
                ClassManager.updateClassResume(classid, updatecresume);
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("SUCCESS");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        // delete student from class
        if (delstudent != null) {
            ClassManager.deleteStudent(classid, delstudent);
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("SUCCESS");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        // delete a class
        if (deleteclass != null && deleteclass.equals("true")) {
            ClassManager.deleteClass(classid);
            resp.sendRedirect("/subsys_interactive/teacher.jsp");
            return;
        }
        
    }
    

}
