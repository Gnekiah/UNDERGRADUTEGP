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
import udai.Verification;

@WebServlet("/AdminDelStudentServlet")
public class AdminDelStudentServlet extends HttpServlet {

    private static final long serialVersionUID = -6887774979624783474L;

    public AdminDelStudentServlet() {
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
        String getsect = req.getParameter("getsect");
        String getone  = req.getParameter("getone");
        String dellist = req.getParameter("dellist");
        String delone  = req.getParameter("delone");
        String retStr = "";
        String id, name;
        /* do return teacher's list */
        if (getlist != null && getlist != "" && getlist.equals("true")) {
            List<Student> students = AccountManager.getStudents();
            for (Student s : students) {
                id = s.getId();
                name = s.getName();
                if (name == null)
                    name = " ";
                retStr += id + "^" + name + "|";
            }
            if (retStr.length() > 0) 
                retStr = retStr.substring(0, retStr.length()-1);
            else
                retStr = "�����ݣ�";
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(retStr);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
            
        }
        /* get students by sectors */
        if (getsect != null && getsect != "") {
            String[] sects = getsect.split("-");
            int sectbeg = Integer.valueOf(sects[0]);
            int sectend = Integer.valueOf(sects[1]);
            if (sects.length != 2 || sectbeg > sectend) {
                retStr = "��������";
            } else if (sectend - sectbeg > 1000){
                retStr = "�������������1000���ڵ����䷶Χ��";
            }else {
                Student s = null;
                for (int i=sectbeg; i <= sectend; i++) {
                    s = AccountManager.getStudent(String.valueOf(i));
                    if (s == null)
                        continue;
                    id = s.getId();
                    name = s.getName();
                    if (name == null)
                        name = " ";
                    retStr += id + "^" + name + "|";
                }
                if (retStr.length() > 0) 
                    retStr = retStr.substring(0, retStr.length()-1);
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(retStr);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        /* get a student info */
        if (getone != null && getone != "") {
            Student s = AccountManager.getStudent(getone);
            if (s == null) {
                retStr = "�˺Ų����ڣ�";
            }
            else {
                id = s.getId();
                name = s.getName();
                if (name == null)
                    name = " ";
                retStr = id + "^" + name;
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(retStr);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        /* delete a student */
        if (delone != null) {
            char ret = AccountManager.unregisterStudent(delone, Global.ROOT_ID);
            switch(ret) {
            case Global.PERMISSION_DENIED:
                retStr = "û�в���Ȩ�ޣ�";
                break;
            case Global.USER_NOT_EXIST:
                retStr = "�û������ڣ�";
                break;
            case Global.SUCCESS:
                retStr = "ɾ���ɹ���";
                break;
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(retStr);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        /* delete students by list */
        if (dellist != null) {
            String[] ids = dellist.split("\\|");
            int succ = 0;
            int fail = 0;
            for (String s : ids) {
                if (AccountManager.unregisterStudent(s, Global.ROOT_ID) == Global.SUCCESS) {
                    succ++;
                }
                else {
                    fail++;
                }
            }
            retStr = "ɾ���ɹ���" + succ + "     ɾ��ʧ�ܣ�" + fail;
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(retStr);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
    }
}
