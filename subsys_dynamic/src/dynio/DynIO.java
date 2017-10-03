package dynio;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import compile.Compile;

public class DynIO {
    
    Queue<Testunit> queue = null;
    
    public DynIO() {
        initDir();
    }
    
    // ³õÊ¼»¯Ä¿Â¼
    public void initDir() {
        File f = null;
        f = new File(Global.GLOBAL_DIR);
        if (!f.isDirectory()) {
            f.mkdir();
            if (Global.DEBUG)
                System.out.println("INIT: Create DIR. " + f.getAbsolutePath());
        }
    }
    
    
    public void doStart() {
        queue = new LinkedList<Testunit>();
        DataListener listener = new DataListener(queue);
        listener.run();
        Testunit tu = null;
        while (true) {
            try {
                Thread.sleep(Global.SLEEP);
            } catch (InterruptedException e) {
                if (Global.DEBUG)
                    e.printStackTrace();
            }
            if (queue.isEmpty())
                continue;
            
            tu = queue.poll();
            if (tu.getType().equals("c")) {
                if (Compile.compileC(tu.getSrcpath(), tu.getStudentpath())) {
                    
                }
                
                // TODO if compile return true, do run and generate report
                // TODO else generate report with error information
                // TODO send report to master
                continue;
            }
            
            if (tu.getType().equals("c++")) {
                Compile.compileCpp(tu.getSrcpath(), tu.getStudentpath());
                // TODO
                // TODO
                // TODO
                continue;
            }
            
            if (tu.getType().equals("java")) {
                Compile.compileJava(tu.getSrcpath(), tu.getStudentpath());
                // TODO
                // TODO
                // TODO
                continue;
            }
        }
    }

    
    
    public static void main(String[] s) {
        DynIO dynio = new DynIO();
        dynio.doStart();
    }
    
}
