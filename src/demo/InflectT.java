package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class InflectT {
    public static void main(String[] s) throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loader.loadClass("demo.InflectB");
        
        Constructor<?> cons = clazz.getDeclaredConstructor((Class[]) null);
        InflectB mIn = (InflectB) cons.newInstance();
        
        
        Method m = clazz.getMethod("p", String.class);
        m.invoke(mIn, "p1");
        m = clazz.getMethod("p2", String.class, String.class);
        m.invoke(mIn, "this", "p2");
        m = clazz.getMethod("p3", String.class, String.class, int.class);
        m.invoke(mIn, "this", "p3", 10086);
        
        
        /*
        mIn.p("1234567");
        
        mIn.p2("1234", "werwew");
        
        mIn.p3("12131321321", "12313ddddddddddddddddddewrdwe", 10000);
        */
        
    }
}
