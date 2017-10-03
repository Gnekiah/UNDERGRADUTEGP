package udai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import interactive.Global;

public class CommonOps {

    public static String timestamp2Date(long ts) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(ts));
    }
    
    public static long date2Timestamp(String tm) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(tm).getTime();
        } catch (ParseException e) {
            if (Global.DEBUG)
                e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
    
    public static String osCheck() {
        String ostype = System.getProperty("os.name");
        if (ostype.length() >= 7 && ostype.substring(0, 7).equals("Windows")){
            return "Windows";
        } else {
            return "Linux";
        }
    }
}
