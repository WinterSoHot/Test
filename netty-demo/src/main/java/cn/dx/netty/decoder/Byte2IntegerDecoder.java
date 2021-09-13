package cn.dx.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/24
 */
public class Byte2IntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= 4) {
            int i = byteBuf.readInt();
            System.out.println("Decode A Integer:" + i);
            list.add(i);
        }
    }
}
