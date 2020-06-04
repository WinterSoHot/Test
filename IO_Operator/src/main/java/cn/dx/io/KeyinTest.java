package cn.dx.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName KeyinTest
 * @Description 使用转换流(字节流转换维字符流)读取键盘输入
 * @Author dongxian
 * @Date 20-5-14 上午9:40
 * @Version 1.0
 **/
public class KeyinTest {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        //使用上面的字符流仍然不方便，因此使用BufferedReader再次包装
        BufferedReader bfr = new BufferedReader(isr);
        // 上面的Reader具有缓存的作用，以换行符为标识，没有读到换行符，则程序阻塞
        String line = null;
        while ((line = bfr.readLine()) != null) {
            if (line.equals("exit")) {
                System.exit(1);
            }
            System.out.println("输出的内容为：" + line);
        }
    }
}
