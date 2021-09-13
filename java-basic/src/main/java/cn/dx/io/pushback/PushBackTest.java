package cn.dx.io.pushback;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * @ClassName PushBackTest
 * @Description 推回输入流：能够将字节/字符数组内容推回缓冲区内
 * @Author dongxian
 * @Date 20-5-14 上午9:45
 * @Version 1.0
 **/
public class PushBackTest {

    public static void main(String[] args) throws IOException {
        PushbackReader pbr = new PushbackReader(new FileReader("test"), 64);//第二个参数维缓冲区大小
        char[] buffer = new char[32];
        String lastContent = "";
        int hasRead = 0;
        while ((hasRead = pbr.read(buffer)) > 0) {
            String content = "Other" + new String(buffer, 0, hasRead);
            pbr.unread(content.toCharArray());
            char[] buf2 = new char[content.toCharArray().length];
            pbr.read(buf2);
            System.out.println(new String(buf2, 0, buf2.length));
        }
        pbr.close();
    }
}
