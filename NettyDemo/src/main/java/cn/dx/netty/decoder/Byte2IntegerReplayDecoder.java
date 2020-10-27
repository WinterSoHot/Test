package cn.dx.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class Byte2IntegerReplayDecoder extends ReplayingDecoder<Integer> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i = byteBuf.readInt();
        System.out.println("Decode A Integer:" + i);
        list.add(i);
    }
}
