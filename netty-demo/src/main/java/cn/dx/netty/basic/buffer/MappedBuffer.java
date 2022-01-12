package cn.dx.netty.basic.buffer;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class MappedBuffer {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("test", "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mappedByteBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        mappedByteBuffer.put(0, (byte) 97);
        mappedByteBuffer.put(1023, (byte) 22);
        raf.close();
    }
}
