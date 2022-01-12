package cn.dx.netty.basic.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class FileOutputDemo {
    static private final byte[] message = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("text.txt");
        FileChannel fc = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (byte item : message) {
            buffer.put(item);
        }
        buffer.flip();
        fc.write(buffer);
        fos.close();
    }
}
