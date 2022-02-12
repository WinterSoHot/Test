package cn.dx.netty;

import cn.dx.netty.decoder.IntegerAddDecoder;
import cn.dx.netty.handler.IntegerProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class IntegerAddDecoderTest {
    @Test
    public void test1() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                // 两个整型转码求和
                ch.pipeline().addLast(new IntegerAddDecoder());
                // 输出整形
                ch.pipeline().addLast(new IntegerProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeInt(i);
            channel.writeInbound(byteBuf);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}