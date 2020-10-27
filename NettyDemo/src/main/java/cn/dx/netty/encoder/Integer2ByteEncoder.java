package cn.dx.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class Integer2ByteEncoder extends MessageToByteEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(integer);
        System.out.println("Encoder Integer = " + integer);
    }
}
