package cn.dx.netty.im;

import cn.dx.netty.im.bean.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/2
 */
public class ProtoBufDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 打标记 ，当前读取的位置
        in.markReaderIndex();
        // 判断包头的长度
        if (in.readableBytes() < 2) {
            // 包头不够
            return;
        }

        // 长度
        int len = in.readShort();
        if (len < 0) {
            ctx.close();
        }
        if (len > in.readableBytes()) {
            // 读取的消息体长度小于传送过来的消息长度
            in.resetReaderIndex();
            return;
        }
        // 省略魔数、版本号等其他信息的读取
        byte[] bytes;
        if (in.hasArray()) {
            // 堆缓存
            ByteBuf slice = in.slice();
            bytes = slice.array();
        } else {
            // 直接缓存
            bytes = new byte[len];
            in.readBytes(bytes, 0, len);
        }
        // Byte 转 POJO
        ProtoMsg.Message msg = ProtoMsg.Message.parseFrom(bytes);
        if (msg != null) {
            out.add(msg);
        }

    }
}
