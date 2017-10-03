package interactive;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataunit.Teacher;
import udai.AccountManager;
import udai.Verification;

@WebServlet("/AdminDelTeacherServlet")
public class AdminDelTeacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1368824688648771784L;

    public AdminDelTeacherServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies, true) != Global.TEACHER) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        
        String getlist = req.getParameter("getlist");
        String deluser = req.getParameter("deluser");
        String delall  = req.getParameter("delall");
        String retStr = "";
        /* do return teacher's list */
        if (getlist != null && getlist.equals("true")) {
            List<Teacher> teachers = AccountManager.getTeachers();
            String id, name;
            for (Teacher teacher : teachers) {
                id = teacher.getId();
                name = teacher.getName();
                // 防止将 root 删除
                if (id.equals(Global.ROOT_ID)) 
                    continue;
                if (name == null)
                    name = " ";
                retStr += id + "^" + name + "|";
            }
            if (retStr.length() > 0) 
                retStr = retStr.substring(0, retStr.length()-1);
            else
                retStr = "无数据！";
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(retStr);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        if (delall != null && delall.equals("true")) {
            List<Teacher> teachers = AccountManager.getTeachers();
            for (Teacher t : teachers) {
                AccountManager.unregisterTeacher(t.getId(), Global.ROOT_ID);
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("SUCCESS");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }

        /* do delete user */
        char ret = AccountManager.unregisterTeacher(deluser, Global.ROOT_ID);
        switch (ret) {
        case Global.PERMISSION_DENIED:
            retStr = "PERMISSION DENIED";
            break;
        case Global.OPERATION_ILLEGAL:
            retStr = "OPERATION ILLEGAL";
            break;
        case Global.SUCCESS:
            retStr = "SUCCESS";
            break;
        case Global.USER_NOT_EXIST:
            retStr = "USER NOT EXIST";
            break;
        case Global.FAILURE:
            retStr = "FAILURE";
            break;
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(retStr);
        resp.getWriter().flush();
        resp.getWriter().close();
        return;
    }

}
