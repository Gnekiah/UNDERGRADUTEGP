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
import jsonunit.StudentJson;
import udai.AccountManager;
import udai.ClassViewer;
import udai.Verification;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public StudentServlet() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.STUDENT) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        
        String id = "";
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) {
                id = c.getValue();
                break;
            }
        }
        String getclass = req.getParameter("getclass");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String resume = req.getParameter("resume");
        String passwd = req.getParameter("password");
        
        // get class info and user info
        if (getclass != null && getclass.equals("true")) {
            List<CClass> classes = ClassViewer.getClasses(id);
            Student s = AccountManager.getStudent(id);
            StudentJson tj = new StudentJson(s, classes);
            String result = new Gson().toJson(tj);
            // set json as data type returned
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(result);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        if (passwd != null) {
            String result = "";
            if (AccountManager.passwdLegalityCheck(passwd) == Global.PASSWD_ILLEGAL) {
                result = "密码修改失败，请保证密码长度为6-20位！";
            }
            else {
                AccountManager.updateStudentPasswd(id, passwd);
                for (Cookie c : cookies) {
                    if (c.getName().equals("password")) {
                        c.setValue(Verification.cookieGen(id));
                        c.setMaxAge(Global.COOKIE_LIVING);
                        resp.addCookie(c);
                        break;
                    }
                }
                result = "SUCCESS";
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(result);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        if (name != null) {
            AccountManager.updateStudentName(id, name);
        }
        if (email != null) {
            AccountManager.updateStudentEmail(id, email);
        }
        if (resume != null) {
            AccountManager.updateStudentResume(id, resume);
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write("SUCCESS");
        resp.getWriter().flush();
        resp.getWriter().close();
	}

}
