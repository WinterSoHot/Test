package cn.dx.io.redirect;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @ClassName RedirectOut
 * @Description 重定向输出
 * @Author dongxian
 * @Date 20-5-14 上午9:54
 * @Version 1.0
 **/
public class RedirectOut {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("out"));
        System.setOut(ps);
        System.out.println("输出到文件中");
    }
}
