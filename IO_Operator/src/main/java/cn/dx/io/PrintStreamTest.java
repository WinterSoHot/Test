package cn.dx.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @ClassName PrintStreamTest
 * @Description 处理流用法
 * @Author dongxian
 * @Date 20-5-14 上午9:20
 * @Version 1.0
 **/
public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("test");
        PrintStream ps = new PrintStream(fos);

        ps.println("普通字符串");
        ps.println(new PrintStreamTest());
        // 使用处理流包装底层输入输出流，只需要关闭处理流，底层流都会自动关闭
        ps.close();
    }
}
