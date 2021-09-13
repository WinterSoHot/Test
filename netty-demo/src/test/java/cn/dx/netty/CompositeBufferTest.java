package cn.dx.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/22
 */
public class CompositeBufferTest {
    static Charset utf8 = StandardCharsets.UTF_8;

    @Test
    public void byteComposite() {
        CompositeByteBuf cbuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        ByteBuf headBuffer = Unpooled.copiedBuffer("疯狂创客圈:", utf8);
        ByteBuf bodyBuffer = Unpooled.copiedBuffer("高性能Netty", utf8);
        cbuf.addComponents(headBuffer, bodyBuffer);
        sendMsg(cbuf);
        // 先增加计数，防止释放
        headBuffer.retain();
        cbuf.release();

        // 重用 headBuffer
        cbuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        bodyBuffer = Unpooled.copiedBuffer("高性能学习社群", utf8);
        cbuf.addComponents(headBuffer, bodyBuffer);
        sendMsg(cbuf);
        cbuf.release();
    }

    private void sendMsg(CompositeByteBuf cbuf) {
        for (ByteBuf buf : cbuf) {
            int len = buf.readableBytes();
            byte[] array = new byte[len];
            buf.getBytes(buf.readerIndex(), array);
            System.out.println(new String(array));
        }
    }
}
