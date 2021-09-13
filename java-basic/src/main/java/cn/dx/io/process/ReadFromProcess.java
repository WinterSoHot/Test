package cn.dx.io.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName ReadFromProcess
 * @Description 读取程序的数据流
 * @Author dongxian
 * @Date 20-5-14 上午9:58
 * @Version 1.0
 **/
public class ReadFromProcess {
    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("javac");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
