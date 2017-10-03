package dynio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class IOCtl {
    
    
    public static void doSend(String rptpath, String ip, int port) {
        Socket client = null;
        DataOutputStream dos = null;
        FileInputStream fis = null;
        try {
            client = new Socket(ip, port);
            fis = new FileInputStream(new File(rptpath));
            dos = new DataOutputStream(client.getOutputStream());
            byte[] send = new byte[Global.MAX_SEND];
            int len = 0;
            while((len = fis.read(send, 0, Global.MAX_SEND)) > 0) {
                dos.write(send, 0, len);
                dos.flush();
            }
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return;
        }
        finally {
            try {
                fis.close();
                dos.close();
                client.close();
            } catch (IOException e1) {
                if (Global.DEBUG)
                    e1.printStackTrace();
            }
        }
    }
    
    
    @SuppressWarnings("resource")
    public static void doAccept(DataInputStream dis, String savepath) {
        FileOutputStream fos = null;
        int readlen = 0;
        try {
            fos = new FileOutputStream(new File(savepath));
            byte[] accept = new byte[Global.MAX_ACCEPT];
            while(true) {
                readlen = dis.read(accept);
                if (readlen == -1)
                    break;
                fos.write(accept, 0, readlen);
                fos.flush();
            }
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return;
        }
    }

}
