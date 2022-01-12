package cn.dx.netty.basic.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class FileInputDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("text.txt");
        FileChannel fc = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.read(buffer);

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println((char) buffer.get());
        }
        fis.close();
    }
}
