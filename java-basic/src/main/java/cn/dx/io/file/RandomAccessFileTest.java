package cn.dx.io.file;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @ClassName RandomAccessFileTest
 * @Description RandomAccessFile使用,RandomAccessFile无法插入数据，只移动到某个位置，覆盖数据
 * @Author dongxian
 * @Date 20-5-14 上午10:59
 * @Version 1.0
 **/
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        String filePath = RandomAccessFileTest.class
                .getClassLoader().getResource("test").getFile();
        System.out.println(filePath);
        RandomAccessFile raf = new RandomAccessFile(filePath, "r");
        System.out.println("RandomAccessFile的初始指针位置：" + raf.getFilePointer());
        raf.seek(3); //移动指针位置到第三个字节
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        while ((hasRead = raf.read(buffer)) > 0) {
            System.out.println(new String(buffer, 0, hasRead));
        }
        raf.close();

        RandomAccessFile raf2 = new RandomAccessFile("out", "rw");
        raf2.seek(raf2.length());
        raf2.write("追加内容\r\n".getBytes());
        raf2.close();
    }
}
