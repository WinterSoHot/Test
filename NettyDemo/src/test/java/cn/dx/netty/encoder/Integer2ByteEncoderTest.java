package cn.dx.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class Integer2ByteEncoderTest {
    @Test
    public void testIntegerToByteDecoder() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new Integer2ByteEncoder());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for (int i = 0; i < 100; i++) {
            channel.write(i);
        }
        channel.flush();
        ByteBuf buf = channel.readOutbound();
        while (null != buf) {
            System.out.println("o = " + buf.readInt());
            buf = channel.readOutbound();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringToIntergerDecoder() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new Integer2ByteEncoder());
                embeddedChannel.pipeline().addLast(new String2IntegerEncoder());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for (int i = 0; i < 100; i++) {
            channel.write("i am " + i);
        }
        channel.flush();
        ByteBuf buf = channel.readOutbound();
        while (null != buf) {
            System.out.println("o = " + buf.readInt());
            buf = channel.readOutbound();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}