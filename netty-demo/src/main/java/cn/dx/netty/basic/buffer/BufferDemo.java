package cn.dx.netty.basic.buffer;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author gudongxian
 * @date 2022/1/12
 */

public class BufferDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream("test");
        FileChannel channel = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化", buffer);

        channel.read(buffer);
        output("调用read()", buffer);

        buffer.flip();
        output("调用flip()", buffer);

        while (buffer.hasRemaining()) {
            byte b = buffer.get();
        }
        output("调用get()", buffer);

        buffer.clear();
        output("调用clear()", buffer);

        fin.close();
    }

    private static void output(String step, Buffer buffer) {
        System.out.println(step + " : ");
        System.out.println("capacity: " + buffer.capacity() + ", ");
        System.out.println("position: " + buffer.position() + ", ");
        System.out.println("limit: " + buffer.limit() + "");
        System.out.println();
    }
}
