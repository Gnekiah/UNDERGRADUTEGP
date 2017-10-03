package udai;

import java.io.File;

import dao.WorkDao;
import dataunit.Work;
import interactive.Global;

public class WorkManager {
    
    public static char addWork(String wid, String cid) {
        Work w = new Work();
        w.setId(wid);
        w.SetTitle("«Î ‰»Î±ÍÃ‚");
        w.setContent("null");
        w.setCodetype("null");
        w.setReferto("null");
        w.setTestcasein("null");
        w.setTestcaseout("null");
        w.setKeylines("null");
        w.setLimcpu(0);
        w.setLimmem(0);
        w.setDotest(0);
        w.setTime(System.currentTimeMillis());
        w.setCid(cid);
        boolean ret = WorkDao.add(w);
        if (!ret) 
            return Global.FAILURE;
        return Global.SUCCESS;
    }
    
    public static void deleteWork(String wid) {
        WorkDao.delete(wid);
        FileOps.delFile(Global.CONTENT_DIR + wid);
        FileOps.delFile(Global.REFERTO_DIR + wid);
        FileOps.delFile(Global.TESTCASEIN_DIR + wid);
        FileOps.delFile(Global.TESTCASEOUT_DIR + wid);
    }
    
    public static void downloadCodes(String wid) {
        // TODO: download all codes in this work
    }
    
    public static void updateTitle(String wid, String title) {
        WorkDao.updateTitle(title, wid);
    }
    
    public static void updateContent(String wid, String content) {
        File f = new File(Global.CONTENT_DIR + wid);
        FileOps.saveFile(f, content);
        WorkDao.updateContent("notnull", wid);
    }

    public static void updateCodetype(String wid, String codetype) {
        WorkDao.updateCodetype(codetype, wid);
    }
    
    public static void updateReferto(String wid, String referto) {
        File f = new File(Global.REFERTO_DIR + wid);
        FileOps.saveFile(f, referto);
        WorkDao.updateReferto("notnull", wid);
    }
    
    public static void updateTestcasein(String wid, String testcasein) {
        File f = new File(Global.TESTCASEIN_DIR + wid);
        FileOps.saveFile(f, testcasein);
        WorkDao.updateTestcasein("notnull", wid);
    }
    
    public static void updateTestcaseout(String wid, String testcaseout) {
        File f = new File(Global.TESTCASEOUT_DIR + wid);
        FileOps.saveFile(f, testcaseout);
        WorkDao.updateTestcaseout("notnull", wid);
    }
    
    public static void updateKeylines(String wid, String keylines) {
        WorkDao.updateKeylines(keylines, wid);
    }
    
    public static void updateCpu(String wid, String cpu) {
        int limcpu = Integer.valueOf(cpu);
        WorkDao.updateLimcpu(limcpu, wid);
    }
    
    public static void updateMem(String wid, String mem) {
        int limmem = Integer.valueOf(mem);
        WorkDao.updateLimmem(limmem, wid);
    }
    
    public static void startDotest(String wid) {
        WorkDao.startDotest(wid);
    }
    
    public static void endDotest(String wid) {
        WorkDao.endDotest(wid);
    }
    
    public static void updateTime(String wid, long time) {
        WorkDao.updateTime(time, wid);
    }
    
    public static boolean doingTest(String wid) {
        Work w = WorkDao.getById(wid);
        if (w == null) {
            return true;
        }
        if (w.getDotest() == 1) {
            return true;
        }
        return false;
    }
    
}
