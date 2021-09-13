package cn.dx.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author dongxian
 * @version 1.0
 * @className RandomFileChannelTest
 * @description TODO
 * @date 20-5-15 下午12:25
 **/
public class RandomFileChannelTest {
    public static void main(String[] args) throws IOException {
        File f = new File("test");
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        FileChannel channel = raf.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());

        channel.position(f.length());

//        channel.write(buffer); 将原来内容在写入

        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.put("我就试试");
        charBuffer.flip();
        Charset charset = StandardCharsets.UTF_8;
        CharsetEncoder encoder = charset.newEncoder();
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        channel.write(byteBuffer);

    }
}
