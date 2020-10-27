package cn.dx.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * 使用Codec同时实现一对编码解码组合
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class Byte2IntegerCodec extends ByteToMessageCodec<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(integer);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 4) {
            int i = byteBuf.readInt();
            list.add(i);
        }
    }
}
