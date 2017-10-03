package interactive;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReplyDao;
import dataunit.Reply;
import udai.FileOps;
import udai.Verification;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 3594536833519233500L;
    

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        if (Verification.doCookiesVer(cookies) != Global.STUDENT) {
            resp.sendRedirect("/subsys_interactive/login.jsp");
            return;
        }
        
        String wid = null;
        String sid = null;
        
        for (Cookie c : cookies) {
            if (c.getName().equals("workid")) {
                wid = c.getValue();
                break;
            }
        }
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) {
                sid = c.getValue();
                break;
            }
        }
        
        if (wid == null || sid == null) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("上传失败！");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        
        String path = Global.UPLOADCODE_DIR + wid;
        File f = new File(path);
        if (!f.isDirectory()) {
            f.mkdir();
        }
        boolean flag = FileOps.saveFile(path, sid, req.getInputStream());
        String ret = null;
        if (flag) {
            Reply rr = ReplyDao.getByWS(wid, sid);
            if (rr != null) {
                ReplyDao.updateSource("source", rr.getId());
                ReplyDao.updateTime(System.currentTimeMillis(), rr.getId());
            } else {
                Reply r = new Reply(Verification.saltGen().substring(0,8), "source",
                        0, 0, "null", "null", System.currentTimeMillis(), sid, wid);
                ReplyDao.add(r);
            }
            ret = "上传成功！";
        } else {
            ret = "上传失败！";
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(ret);
        resp.getWriter().flush();
        resp.getWriter().close();
        return;

    }

}
