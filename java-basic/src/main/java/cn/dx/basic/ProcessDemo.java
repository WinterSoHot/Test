package cn.dx.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/17
 */
public class ProcessDemo {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("adb version");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
