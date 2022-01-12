package cn.dx.netty.basic.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class DirectBuffer {

    public static void main(String[] args) throws Exception {
        String inFile = "text.txt";
        FileInputStream fis = new FileInputStream(inFile);
        FileChannel fc = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("text_copy.txt");
        FileChannel foc = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();
            int flag = fc.read(buffer);
            if (flag == -1) {
                break;
            }
            buffer.flip();
            foc.write(buffer);
        }
    }
}
