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

import dataunit.Student;
import jsonunit.DoTestJson;
import udai.ClassManager;
import udai.DynamicEntry;
import udai.Verification;
import udai.WorkManager;

@WebServlet("/DoTestServlet")
public class DoTestServlet extends HttpServlet {

    private static final long serialVersionUID = 6778374893363403363L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.TEACHER) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        
        //
        String cid = null;
        // work id
        String wid = null;
        // students
        List<Student> students = null;
        // load request
        String loadrq = req.getParameter("loadrq");
        // dotest rq
        String dotestrq = req.getParameter("dotestrq");
        
        // check a work id and class id
        for(Cookie c : cookies) {
            if (c.getName().equals("workid")) {
                wid = c.getValue();
                break;
            }
        }
        for(Cookie c : cookies) {
            if (c.getName().equals("classid")) {
                cid = c.getValue();
                break;
            }
        }
        if (cid == null || wid == null) {
            resp.sendRedirect("/subsys_interactive/work.jsp");
            return;
        }
        
        // get students of the class 
        students = ClassManager.getStudents(cid);
        
        // load request
        if (loadrq != null) {
            DoTestJson dtj = new DoTestJson(students, wid);
            String ret = new Gson().toJson(dtj);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(ret);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        // do test
        if (dotestrq != null) {
            if (!WorkManager.doingTest(wid)) {
                DynamicEntry de = new DynamicEntry(wid);
                de.doTest();
            }
            resp.setContentType("application/html; charset=UTF-8");
            resp.getWriter().write("正在执行测试，请稍后刷新！");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
    }
}
