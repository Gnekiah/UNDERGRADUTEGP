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
import dataunit.Work;
import jsonunit.ClassViewJson;
import jsonunit.WorkViewJson;
import udai.ClassViewer;
import udai.FileOps;
import udai.Verification;

@WebServlet("/ClassViewServlet")
public class ClassViewServlet extends HttpServlet {

    private static final long serialVersionUID = 6253799650617957393L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.STUDENT) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        
        String classid = req.getParameter("classid");
        String classviewreq = req.getParameter("classviewreq");
        
        String workid = req.getParameter("workid");
        String workviewreq = req.getParameter("workviewreq");
        
        String downloadreq = req.getParameter("downloadreq");
        
        
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
            resp.sendRedirect("/subsys_interactive/classview.jsp");
            return;
        }
        
        // save work id
        if (workid != null) {
            boolean flag = true;
            for (Cookie c : cookies) {
                if (c.getName().equals("workid")) {
                    c.setValue(workid);
                    c.setMaxAge(Global.COOKIE_LIVING);
                    resp.addCookie(c);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Cookie c = new Cookie("workid", workid);
                c.setMaxAge(Global.COOKIE_LIVING);
                resp.addCookie(c);
            }
            resp.sendRedirect("/subsys_interactive/workview.jsp");
            return;
        }
        
        // download the file
        if (downloadreq != null) {
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-disposition", "attachment;filename=offline.zip");
            FileOps.getOffline(resp.getOutputStream());
            return;
        }
        
        // get class id
        for (Cookie c : cookies) {
            if (c.getName().equals("classid")) {
                classid = c.getValue();
                break;
            }
        }
        
        // get work id
        for (Cookie c : cookies) {
            if (c.getName().equals("workid")) {
                workid = c.getValue();
                break;
            }
        }
        
        // return class json to client 
        if (classviewreq != null) {
            if (classid == null) {
                resp.sendRedirect("/subsys_interactive/student.jsp");
                return;
            }
            CClass c = ClassViewer.getClass(classid);
            List<Work> works = ClassViewer.getWorks(classid);
            ClassViewJson cvj = new ClassViewJson(c, works);
            String result = new Gson().toJson(cvj);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(result);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }

        // return work info to client
        if (workviewreq != null) {
            if (workid == null) {
                resp.sendRedirect("/subsys_interactive/classview.jsp");
                return;
            }
            Work w = ClassViewer.getWork(workid);
            WorkViewJson wvj = new WorkViewJson(w);
            String ret = new Gson().toJson(wvj);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(ret);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
    }
}
