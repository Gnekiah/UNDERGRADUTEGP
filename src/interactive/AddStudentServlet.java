package interactive;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataunit.Student;
import udai.AccountManager;
import udai.ClassManager;
import udai.Verification;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {

    private static final long serialVersionUID = -7319154417040007914L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.TEACHER) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        // student ids
        String[] sids = req.getParameter("sids").replaceAll("\r", "").split("\n");
        String cid = "";
        String tid = "";
        String result = "";
        String successids = "";
        // get class id 
        for (Cookie c : cookies) {
            if (c.getName().equals("classid")) {
                cid = c.getValue();
            }
            if (c.getName().equals("username")) {
                tid = c.getValue();
            }
        }
        // get students which are already in the class
        List<Student> sidlist = ClassManager.getStudents(cid);
        String[] oldsids = new String[sidlist.size()];
        int cnt = 0;
        for (Student s : sidlist) {
            oldsids[cnt++] = s.getId();
        }
        
        for (String sid : sids) {
            // filter illegal id
            if (sid.length() != 8 || !sid.matches("[0-9]+")) {
                result += sid + ",";
                continue;
            }
            // filter ids that existed
            boolean flag = false;
            for (String s : oldsids) {
                if (s.equals(sid))
                    flag = true;
            }
            if (flag)
                continue;
            // do add student to class
            if (AccountManager.getStudent(sid) == null) {
                AccountManager.registerStudent(sid, sid, tid);
                ClassManager.addStudent(cid, sid);
            }
            else {
                ClassManager.addStudent(cid, sid);
            }
            successids = successids + sid + ",";
        }
        if (result == "") {
            result = "SUCCESS" + successids.substring(0, successids.length()-1);
        }
        else {
            result += "以上账号未能添加成功！";
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(result);
        resp.getWriter().flush();
        resp.getWriter().close();
        return;
    }
}
