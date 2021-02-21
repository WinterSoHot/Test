package cn.dx.java.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/10
 */
public class CheckException {

    public static void main(String[] args) throws IOException {
        File file;
        FileInputStream fis = new FileInputStream("tr");
        int k;
        while ((k = fis.read()) != -1) {
            System.out.print((char) k);
        }
        fis.close();


    }
}



