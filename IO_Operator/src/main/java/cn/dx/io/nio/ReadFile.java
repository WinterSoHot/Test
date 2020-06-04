package cn.dx.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author dongxian
 * @version 1.0
 * @className ReadFile
 * @description TODO
 * @date 20-5-15 下午12:29
 **/
public class ReadFile {
    public static void main(String[] args) throws IOException {
        File f = new File("test");
        FileChannel inChannel = new FileInputStream(f).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(256);

        Charset charset = StandardCharsets.UTF_8;

        while (inChannel.read(buffer) != -1) {
            buffer.flip(); // 读取结束，准备读出

            // 解码ByteBuffer
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println(charBuffer.toString());

            buffer.clear(); // 读取结束，准备写入
        }
    }
}
