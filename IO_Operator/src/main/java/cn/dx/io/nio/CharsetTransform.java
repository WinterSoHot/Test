package cn.dx.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

/**
 * @author dongxian
 * @version 1.0
 * @className CharsetTransform
 * @description TODO
 * @date 20-5-15 下午12:51
 **/
public class CharsetTransform {
    public static void main(String[] args) throws CharacterCodingException {
        Charset charset = StandardCharsets.UTF_8;
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.put("你好啊");
        charBuffer.flip();

        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get(i) + " ");
        }
        System.out.println("\n"+decoder.decode(byteBuffer).toString());
    }
}
