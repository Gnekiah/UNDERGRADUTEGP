package interactive;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import udai.Verification;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 421182619498541969L;

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        InitAll.init();
        if (Global.DEBUG)
            System.out.println("SERVER INITED!");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // clear all cookies and later generate cookies
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            c.setMaxAge(0);
            resp.addCookie(c);
        }
        // get login user and password
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // without passwd or username
        if (username == null || password == null) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        
        char ret = Verification.loginVer(username, password);
        if (ret == Global.STUDENT) {
            Cookie usercookie = new Cookie("username", username);
            usercookie.setMaxAge(Global.COOKIE_LIVING);
            resp.addCookie(usercookie);
            Cookie passwdcookie = new Cookie("password", Verification.cookieGen(username));
            passwdcookie.setMaxAge(Global.COOKIE_LIVING);
            resp.addCookie(passwdcookie);
            resp.sendRedirect("/subsys_interactive/student.jsp");
            return;
        }
        else if (ret == Global.TEACHER) {
            Cookie usercookie = new Cookie("username", username);
            usercookie.setMaxAge(Global.COOKIE_LIVING);
            resp.addCookie(usercookie);
            Cookie passwdcookie = new Cookie("password", Verification.cookieGen(username));
            passwdcookie.setMaxAge(Global.COOKIE_LIVING);
            resp.addCookie(passwdcookie);
            resp.sendRedirect("/subsys_interactive/teacher.jsp");
            return;
        }
        resp.sendRedirect("/subsys_interactive/login.jsp");
        return;
    }

}
