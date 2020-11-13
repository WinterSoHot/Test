package cn.dx.netty.im;

import cn.dx.netty.im.bean.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义ProtoBuf 编码器
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/2
 */
public class ProtoBufEncoder extends MessageToByteEncoder<ProtoMsg.Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoMsg.Message msg, ByteBuf byteBuf) throws Exception {
        byte[] bytes = msg.toByteArray();
        // 将消息长度写入，也就是消息头，这里只用两个字节，最大为32767
        byteBuf.writeShort(bytes.length);
        // 省略魔数，版本号和其他信息，和上面的长度类似
        // 消息体中包含我们要发送的数据
        byteBuf.writeBytes(bytes);
    }
}
