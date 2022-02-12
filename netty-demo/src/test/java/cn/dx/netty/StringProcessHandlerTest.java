package cn.dx.netty;

import cn.dx.netty.decoder.StringIntegerHeaderDecoder;
import cn.dx.netty.decoder.StringReplayDecoder;
import cn.dx.netty.handler.StringProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class StringProcessHandlerTest {
    static String content = "疯狂创客圈：高性能学习社群!";

    @Test
    public void test1() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                // 基于ReplayingDecoder 解析字符串
                ch.pipeline().addLast(new StringReplayDecoder());
                // 输出整形
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        for (int j = 0; j < 100; j++) {
            //1-3之间的随机数
            int random = new Random().nextInt(3) + 1;
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(bytes.length * random);
            for (int k = 0; k < random; k++) {
                buf.writeBytes(bytes);
            }
            channel.writeInbound(buf);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                // 基于ByteToMessageDecoder 解析字符串
                ch.pipeline().addLast(new StringIntegerHeaderDecoder());
                // 输出整形
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        for (int j = 0; j < 100; j++) {
            //1-3之间的随机数
            int random = new Random().nextInt(3) + 1;
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(bytes.length * random);
            for (int k = 0; k < random; k++) {
                buf.writeBytes(bytes);
            }
            channel.writeInbound(buf);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}