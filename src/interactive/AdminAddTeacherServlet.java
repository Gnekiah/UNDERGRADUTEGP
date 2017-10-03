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
        
	    final String RES_EXISE = "ע��ʧ�ܣ��û��Ѵ��ڣ�";
	    final String RES_ILLEGAL = "ע��ʧ�ܣ��˺Ų���Ϊ�����֣�";
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null && password == null) {
		    resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("�˺����벻��Ϊ�գ�");
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
		    ret = "ע��ʧ�ܣ�";
		    break;
		case Global.SUCCESS:
		    ret = "ע��ɹ���";
		    break;
		case Global.ID_ILLEGAL:
		    ret = "�˺ŷǷ���������5-8λ���ȵ��˺�";
		    break;
		case Global.PASSWD_ILLEGAL:
		    ret = "����Ƿ���������6-20λ���ȵĿ���";
		    break;
		case Global.PERMISSION_DENIED:
		    ret = "û�в���Ȩ�ޣ�";
		    break;
		}
		resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(ret);
        resp.getWriter().flush();
        resp.getWriter().close();
        return;
		
	}

}
