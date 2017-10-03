package udai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

import dao.ReplyDao;
import dao.WorkDao;
import dataunit.Reply;
import dataunit.Work;
import interactive.Global;

public class DynamicEntry {
    public String wid = null;
    public List<Reply> replys = null;
    public Work work = null;
    public String codetype = null;
    public String casein = null;
    public String caseout = null;
    public long limcpu = 0;
    public long limmem = 0;

    public DynamicEntry(String w) {
        this.wid = w;
        replys = ReplyDao.getByWid(w);
        work = WorkDao.getById(w);
        codetype = work.getCodetype();
        casein = work.getTestcasein();
        caseout = work.getTestcaseout();
        limcpu = work.getLimcpu();
        limmem = work.getLimmem();
    }

    public void doTest() {
        for (Reply r : replys) {
            String src = r.getSource();
            if (src.equals("report")) {
                continue;
            } else if (!src.equals("source")) {
                ReplyDao.updateStaticscore(0, r.getId());
                ReplyDao.updateStaticscore(0, r.getId());
                continue;
            }
            int i = 10;
            while ((--i) > 0) {
                String compileErr = "";
                if (codetype.equals("C") || codetype.equals("c")) {
                    compileErr = compileC(Global.UPLOADCODE_DIR + wid + "/" + r.getSid(), Global.UPLOADCODE_DIR + wid);
                    if (compileErr.length() <= 1) {
                        break;
                    }
                    correctC(compileErr, Global.UPLOADCODE_DIR + wid + "/" + r.getSid());
                } else if (codetype.equals("C++") || codetype.equals("c++")) {
                    compileErr = compileCpp(Global.UPLOADCODE_DIR + wid + "/" + r.getSid(), Global.UPLOADCODE_DIR + wid);
                    if (compileErr.length() <= 1) {
                        break;
                    }
                    correctCpp(compileErr, Global.UPLOADCODE_DIR + wid + "/" + r.getSid());
                } else if (codetype.equals("java") || codetype.equals("JAVA") || codetype.equals("Java")) {
                    compileErr = compileJava(Global.UPLOADCODE_DIR + wid + "/" + r.getSid(), Global.UPLOADCODE_DIR + wid);
                    if (compileErr.length() <= 1) {
                        break;
                    }
                    correctJava(compileErr, Global.UPLOADCODE_DIR + wid + "/" + r.getSid());
                }
            }
            String[] cmd = new String[3];
            cmd[0] = Global.SHELL_PATH;
            cmd[1] = "-c";
            cmd[2] = Global.MONITOR + " a.out 0 0 0 0 0 0 0 0 " + Global.TESTCASEIN_DIR+wid + " usage.usg 0";
            RunTime.doRunTime(cmd, Global.UPLOADCODE_DIR + wid);
        }
    }

    public static void rptDecrypt(String wid, String sid) {
        RunTime.rptDecrypt(wid, sid);
    }

    public void parseReport(String wid, String sid) {
        File f = new File(Global.DYNAMIC_REPORT_DIR + wid + "/" + sid);
        if (!f.isFile()) {
            return;
        }

        // parse cpu and menory used
        // parse test out string
        double usecpu = 0;
        long usemem = 0;
        String testout = "";
        String usages = "";
        BufferedReader br = null;

        boolean isRight = false;
        boolean isCpuok = false;
        boolean isMemok = false;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line = null;
            // flag = 1: report is offline report and line is testout string
            // flag = 2: report is offline report and line is usages
            int flag = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("testout_begin")) {
                    flag = 1;
                    continue;
                }
                if (line.equals("testout_end")) {
                    flag = 2;
                    continue;
                }
                if (flag == 1) {
                    testout += line + "\n";
                    continue;
                }
                if (flag == 2) {
                    usages = line;
                }
            }
            caseout = caseout.replace("\n", "").replace("\r", "").replace(" ", "");
            testout = testout.replace("\n", "").replace("\r", "").replace(" ", "");
            if (caseout.equals(testout)) {
                isRight = true;
            }
            String opts[] = usages.split(",");
            if (opts.length == 18) {
                usecpu = Integer.valueOf(opts[0]) + Integer.valueOf(opts[1]) * 1000;
                usecpu += Integer.valueOf(opts[2]) + Integer.valueOf(opts[3]) * 1000;
                usemem = Integer.valueOf(opts[4]);
                if (usecpu <= limcpu)
                    isCpuok = true;
                if (usemem <= limmem)
                    isMemok = true;
            }
            br.close();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        if (isRight)
            ReplyDao.updateDynamicscore(100, sid);
        else if (isRight || (isCpuok && isMemok)) {
            ReplyDao.updateDynamicscore(100, sid);
        }

    }

    public String compileC(String src, String wd) {
        String[] cmd = new String[3];
        cmd[0] = Global.SHELL_PATH;
        cmd[1] = "-c";
        cmd[2] = "gcc " + src;
        return RunTime.doRunTime(cmd, new File(wd));
    }

    public String compileCpp(String src, String wd) {
        String[] cmd = new String[3];
        cmd[0] = Global.SHELL_PATH;
        cmd[1] = "-c";
        cmd[2] = "g++ " + src;
        return RunTime.doRunTime(cmd, new File(wd));
    }

    public String compileJava(String src, String wd) {
        String[] cmd = new String[3];
        cmd[0] = Global.SHELL_PATH;
        cmd[1] = "-c";
        cmd[2] = "javac -d ./ " + src;
        return RunTime.doRunTime(cmd, new File(wd));
    }
    
    public void correctC(String err, String src) {
        StringBuffer sbsrc = new StringBuffer(FileOps.loadFile(src));
        StringBuffer sberr = new StringBuffer(err);
        // 模式一：判断因为遗漏一行代码末尾的","而引起的错误
        Pattern p1 = Pattern.compile("error: expected ',' or ';' before");
        
        // do match for each line
        if (p1.matcher(sberr).find()) {
            ;
        } 
    }
    
    public void correctCpp(String err, String src) {
        
    }
    
    public void correctJava(String err, String src) {
        
    }

}
