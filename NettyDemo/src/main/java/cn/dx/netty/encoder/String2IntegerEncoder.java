package cn.dx.netty.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class String2IntegerEncoder extends MessageToMessageEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch >= 48 && ch <= 57) {
                list.add((int) ch);
            }
        }
    }
}
