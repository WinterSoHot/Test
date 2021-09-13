package cn.dx.netty.codec;

import cn.dx.netty.decoder.Byte2IntegerDecoder;
import cn.dx.netty.encoder.Integer2ByteEncoder;
import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 组合器将编码器和解码器组合起来
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class IntegerDuplexHandler extends CombinedChannelDuplexHandler<Byte2IntegerDecoder, Integer2ByteEncoder> {
    public IntegerDuplexHandler() {
        super(new Byte2IntegerDecoder(), new Integer2ByteEncoder());
    }
}
