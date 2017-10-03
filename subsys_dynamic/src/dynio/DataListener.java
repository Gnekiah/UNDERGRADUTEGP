package dynio;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

public class DataListener extends Thread {
    
    private Queue<Testunit> queue = null;
    
    public DataListener(Queue<Testunit> queue) {
        this.queue = queue;
    }
    
    
    @SuppressWarnings("resource")
    public void run() {
        ServerSocket server = null;
        DataInputStream dis = null;
        Socket client = null;
        Testunit tunit = null;
        File f = null;
        try {
            server = new ServerSocket(Global.DATA_PORT);
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return;
        }
        while(true) {
            try {
                client = server.accept();
                dis = new DataInputStream(client.getInputStream());
                tunit = new Testunit();
                // get work id
                tunit.setWid(dis.readUTF());
                tunit.setWorkpath(Global.GLOBAL_DIR + tunit.getWid());
                f = new File(tunit.getWorkpath());
                if (!f.isDirectory()) {
                    f.mkdir();
                }
                // get student id
                tunit.setSid(dis.readUTF());
                tunit.setStudentpath(tunit.getWorkpath() + "/" + tunit.getSid());
                f = new File(tunit.getStudentpath());
                if (!f.isDirectory()) {
                    f.mkdir();
                }
                // get type
                tunit.setType(dis.readUTF());
                // get source file
                tunit.setSrcpath(tunit.getStudentpath() + "/" + dis.readUTF());
                IOCtl.doAccept(dis, tunit.getSrcpath());
                // get input file
                tunit.setInpath(tunit.getWorkpath() + "/" + dis.readUTF());
                IOCtl.doAccept(dis, tunit.getInpath());
                // get output file
                tunit.setOutpath(tunit.getWorkpath() + "/" + dis.readUTF());
                IOCtl.doAccept(dis, tunit.getOutpath());
                // get cpu lim
                tunit.setCpu(dis.readInt());
                // get mem lim
                tunit.setMem(dis.readInt());
                // get tracelevel
                tunit.setTracelevel(dis.readInt());
                dis.close();
                client.close();
            } catch (IOException e) {
                if (Global.DEBUG)
                    e.printStackTrace();
            }
            queue.offer(tunit);
        }
    }

}
