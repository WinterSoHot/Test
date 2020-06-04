package cn.dx.io.file;


import java.io.*;

/**
 * @ClassName FileIODemo
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-13 下午5:36
 * @Version 1.0
 **/
public class FileIODemo {


    public static void main(String[] args) throws IOException {
//        1. 字节输入流
//        FileInputStream fis = new FileInputStream("pom.xml");
//        byte[] buffer = new byte[1024];
//        int hasRead;
//        while ((hasRead = fis.read(buffer)) > 0) {
//            System.out.println(new String(buffer, 0, hasRead));
//        }
//        fis.close();

        //2. 字符输入流
//        FileReader fr = new FileReader("pom.xml");
//        char[] buffer = new char[1024];
//        int hasRead = 0;
//        while ((hasRead = fr.read(buffer)) > 0) {
//            System.out.println(new String(buffer, 0, hasRead));
//        }
//        fr.close();

        //3. 字节输出流
        FileInputStream fis = new FileInputStream("pom.xml");
        FileOutputStream fos = new FileOutputStream("tr");

        byte[] buffer = new byte[1024];
        int hasRead;
        while ((hasRead = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, hasRead);
        }
        fis.close();
        fos.close();
    }
}
