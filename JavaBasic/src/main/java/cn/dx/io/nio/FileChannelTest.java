package cn.dx.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author dongxian
 * @version 1.0
 * @className FileChannelTest
 * @description Channel使用
 * @date 20-5-15 下午12:13
 **/
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        File f = new File("tr2");
        FileChannel inChannel = new FileInputStream(f).getChannel(); // 它获取的只能读取
        FileChannel outChannel = new FileOutputStream("tr2_out").getChannel(); // 只能写入

        // 将文件数据映射到内存中
        MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());

        // 创建字符集来创建解码
        Charset charset = StandardCharsets.UTF_8;
        // 写入字符到channel
        outChannel.write(buffer);
        buffer.clear();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = decoder.decode(buffer);
        System.out.println(charBuffer.toString());
    }
}
