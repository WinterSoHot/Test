package cn.dx.io.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WriteToProcess
 * @Description 输出到进程
 * @Author dongxian
 * @Date 20-5-14 上午10:01
 * @Version 1.0
 **/
public class WriteToProcess {
    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("wc -w <<EOF");
        PrintStream ps = new PrintStream(process.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        ps.println("I am gu dong xian");
        ps.println("EOF"); //输出中断标识

        String line = null;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }
}
