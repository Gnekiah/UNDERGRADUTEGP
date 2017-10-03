package interactive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.WorkDao;
import jsonunit.WorkJson;
import udai.CommonOps;
import udai.FileOps;
import udai.Verification;
import udai.WorkManager;

@WebServlet("/WorkServlet")
public class WorkServlet extends HttpServlet {

    private static final long serialVersionUID = -4714954723112385535L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.TEACHER) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        String classid = "";
        // get work by id
        String workid = req.getParameter("workid");
        // new a work
        String addwork = req.getParameter("addwork");
        // get a work
        String workreq = req.getParameter("workreq");
        // get a title
        String title = req.getParameter("title");
        // get a content
        String content = req.getParameter("content");
        // get a referto
        String referto = req.getParameter("referto");
        // get key lines
        String keylines = req.getParameter("keylines");
        // get test case in
        String testcasein = req.getParameter("testcasein");
        // get test case out
        String testcaseout = req.getParameter("testcaseout");
        // get lim cpu
        String limcpu = req.getParameter("limcpu");
        // get lim mem
        String limmem = req.getParameter("limmem");
        // get time that the work stopped
        String time = req.getParameter("time");
        // get code type
        String codetype = req.getParameter("codetype");
        // download codes in this work
        String downloadreq = req.getParameter("downloadreq");
        // delete the work
        String deletereq = req.getParameter("deletereq");
        
        // get class id from cookie
        for (Cookie c : cookies) {
            if (c.getName().equals("classid")) {
                classid = c.getValue();
            }
        }
        // get work
        if (workid != null) {
            boolean flag = true;
            for(Cookie c : cookies) {
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
            resp.sendRedirect("/subsys_interactive/work.jsp");
            return;
        }
        
        // add a work
        // if work created success, goto work.jsp
        // if created failed, goto error.jsp
        if (addwork != null) {
            workid = Verification.saltGen().substring(0, 8);
            if (WorkManager.addWork(workid, classid) == Global.SUCCESS) {
                boolean flag = true;
                for(Cookie c : cookies) {
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
                resp.sendRedirect("/subsys_interactive/work.jsp");
                return;
            }
            resp.sendRedirect("/subsys_interactive/error.jsp");
            return;
        }
        
        // get work id from cookies
        for(Cookie c : cookies) {
            if (c.getName().equals("workid")) {
                workid = c.getValue();
                break;
            }
        }
        
        // work requset for return work info to client
        if (workreq != null && workid != null) {
            WorkJson wj = new WorkJson(WorkDao.getById(workid));
            String ret = "";
            ret = new Gson().toJson(wj);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(ret);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        // download work's codes
        if (downloadreq != null && workid != null) {
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-disposition", "attachment;filename=" + workid + ".zip");
            FileOps.getPackages(workid, resp.getOutputStream());
            return;
        }
        
        // delete the work
        if (deletereq != null && workid != null) {
            WorkManager.deleteWork(workid);
            resp.sendRedirect("/subsys_interactive/class.jsp");
            return;
        }
        
        // update work
        if (title != null) {
            WorkManager.updateTitle(workid, title);
        }
        
        // update content
        if (content != null) {
            WorkManager.updateContent(workid, content);
        }
        
        // update referto
        if (referto != null) {
            WorkManager.updateReferto(workid, referto);
        }
        
        // update keylines 
        if (keylines != null) {
            WorkManager.updateKeylines(workid, keylines);
        }
        
        // update testcase in
        if (testcasein != null) {
            WorkManager.updateTestcasein(workid, testcasein);
        }
        
        // update testcase out
        if (testcaseout != null) {
            WorkManager.updateTestcaseout(workid, testcaseout);
        }
        
        // update limit cpu
        if (limcpu != null) {
            WorkManager.updateCpu(workid, limcpu);
        }
        
        // update limit mem
        if (limmem != null) {
            WorkManager.updateMem(workid, limmem);
        }
        
        // update limit time
        if (time != null) {
            long tsp = CommonOps.date2Timestamp(time);
            WorkManager.updateTime(workid, tsp);
        }
        
        // update code type
        if (codetype != null) {
            WorkManager.updateCodetype(workid, codetype);
        }
        resp.setContentType("application/html; charset=UTF-8");
        resp.getWriter().write("SUCCESS");
        resp.getWriter().flush();
        resp.getWriter().close();
        return;
    }

}
