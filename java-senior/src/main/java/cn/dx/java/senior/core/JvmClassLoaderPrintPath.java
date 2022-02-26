package cn.dx.java.senior.core;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author gudongxian
 * @date 2022/2/15
 */
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器：");
        for (URL url : urLs) {
            System.out.println(" ==> " + url.toExternalForm());
        }
        ClassLoader cl = JvmClassLoaderPrintPath.class.getClassLoader().getParent();
        printClassLoader("扩展类加载器", cl);
        cl = JvmClassLoaderPrintPath.class.getClassLoader();
        printClassLoader("应用类加载器", cl);
    }

    private static void printClassLoader(String name, ClassLoader cl) {
        if (cl != null) {
            System.out.println(name + " ClassLoader -> " + cl.toString());
            printURLForClassLoader(cl);
        } else {
            System.out.println(name + " ClassLoader -> null");
        }

    }

    private static void printURLForClassLoader(ClassLoader cl) {
        Object ucp = insightField(cl, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object url : ps) {
            System.out.println(" ===> " + url.toString());
        }
    }

    public static Object insightField(Object obj, String fieldName) {
        try {
            Field field = null;
            if (obj instanceof URLClassLoader) {
                field = URLClassLoader.class.getDeclaredField(fieldName);
            } else {
                field = obj.getClass().getDeclaredField(fieldName);
            }
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
