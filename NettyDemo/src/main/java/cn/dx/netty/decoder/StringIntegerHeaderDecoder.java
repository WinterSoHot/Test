package cn.dx.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 实现Head-Content模式读取字符串
 * Head: 存放字符串长度，为一个整型
 * Content: 字符串内容，长度由Head中指定
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class StringIntegerHeaderDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // Head 长度不足够
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        // Head 足够，标记当前位置
        byteBuf.markReaderIndex();
        int length = byteBuf.readInt();
        // Content 长度没有达到Head中的要求
        if (byteBuf.readableBytes() < length) {
            // 重置读取的位置到读取head的之后的位置
            byteBuf.resetReaderIndex();
            return;
        }
        //读取Content
        byte[] inBytes = new byte[length];
        byteBuf.readBytes(inBytes, 0, length);
        list.add(new String(inBytes, StandardCharsets.UTF_8));
    }
}
