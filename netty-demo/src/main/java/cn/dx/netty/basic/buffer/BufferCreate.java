package cn.dx.netty.basic.buffer;

import java.nio.ByteBuffer;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class BufferCreate {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte[] array = new byte[10];
        ByteBuffer buffer1 = ByteBuffer.wrap(array);
    }
}
