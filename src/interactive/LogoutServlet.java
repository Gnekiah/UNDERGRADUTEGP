package interactive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 3951453982525359434L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        session.invalidate();
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            c.setMaxAge(0);
            resp.addCookie(c);
        }
        resp.sendRedirect("/subsys_interactive/login.jsp");
        return;
    }
    
}
