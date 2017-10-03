package interactive;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import udai.AccountManager;
import udai.Verification;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminAddTeacherServlet")
public class AdminAddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AdminAddTeacherServlet() {
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
        
	    final String RES_EXISE = "注册失败，用户已存在！";
	    final String RES_ILLEGAL = "注册失败，账号不能为纯数字！";
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null && password == null) {
		    resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("账号密码不能为空！");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
		}
		    
		char type = Verification.accountType(username);
		// creating user exist
		if (type != Global.USER_NOT_EXIST) {
		    resp.setContentType("text/html; charset=UTF-8");
		    resp.getWriter().write(RES_EXISE);
		    resp.getWriter().flush();
		    resp.getWriter().close();
		    return;
		}
		// user id can not be pure number
		if (username.matches("[0-9]+")) {
		    resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(RES_ILLEGAL);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
		}
		
		String ret = null;
		type = AccountManager.registerTeacher(username, password, Global.ROOT_ID);
		switch(type) {
		case Global.FAILURE:
		    ret = "注册失败！";
		    break;
		case Global.SUCCESS:
		    ret = "注册成功！";
		    break;
		case Global.ID_ILLEGAL:
		    ret = "账号非法！请输入5-8位长度的账号";
		    break;
		case Global.PASSWD_ILLEGAL:
		    ret = "口令非法！请输入6-20位长度的口令";
		    break;
		case Global.PERMISSION_DENIED:
		    ret = "没有操作权限！";
		    break;
		}
		resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(ret);
        resp.getWriter().flush();
        resp.getWriter().close();
        return;
		
	}

}
