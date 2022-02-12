package cn.dx.netty;

import cn.dx.netty.handler.StringProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class NettyOpenBoxDecoderTest {
    static String spliter = "\r\n";
    static String spliter2 = "\t";
    static String content = "疯狂创客圈：高性能学习社群!";

    @Test
    public void testLineBasedFrameDecoder() {
        try {
            ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<EmbeddedChannel>() {
                protected void initChannel(EmbeddedChannel ch) {
                    // 使用换行符分割字符串的解码器
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };
            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 0; j < 100; j++) {
                //1-3之间的随机数
                int random = new Random().nextInt(3) + 1;
                ByteBuf buf = Unpooled.buffer();
                // 写入内容
                for (int k = 0; k < random; k++) {
                    buf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
                }
                // 写入换行符
                buf.writeBytes(spliter.getBytes(StandardCharsets.UTF_8));
                channel.writeInbound(buf);
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelimiterBasedFrameDecoder() {
        try {
            final ByteBuf delimiter =
                    Unpooled.copiedBuffer(spliter2.getBytes(StandardCharsets.UTF_8));
            ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<EmbeddedChannel>() {
                protected void initChannel(EmbeddedChannel ch) {
                    // 使用换行符分割字符串的解码器
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, true, delimiter));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };
            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 0; j < 100; j++) {
                //1-3之间的随机数
                int random = new Random().nextInt(3) + 1;
                ByteBuf buf = Unpooled.buffer();
                // 写入内容
                for (int k = 0; k < random; k++) {
                    buf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
                }
                // 写入分隔符
                buf.writeBytes(spliter2.getBytes(StandardCharsets.UTF_8));
                channel.writeInbound(buf);
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final int VERSION = 100;

    /**
     * LengthFieldBasedFrameDecoder使用实例 1
     */
    @Test
    public void testLengthFieldBasedFrameDecoder1() {
        try {
            final LengthFieldBasedFrameDecoder spliter =
                    new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);
            ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<EmbeddedChannel>() {
                protected void initChannel(EmbeddedChannel ch) {
                    ch.pipeline().addLast(spliter);
                    ch.pipeline().addLast(new
                            StringDecoder(StandardCharsets.UTF_8));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };
            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 1; j <= 100; j++) {
                ByteBuf buf = Unpooled.buffer();
                String s = j + "次发送->" + content;
                byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
                buf.writeInt(bytes.length);
                buf.writeBytes(bytes);
                channel.writeInbound(buf);
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLengthFieldBasedFrameDecoder2() {
        try {
            final LengthFieldBasedFrameDecoder spliter =
                    new LengthFieldBasedFrameDecoder(1024, 0, 4, 2, 6);
            ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<EmbeddedChannel>() {
                protected void initChannel(EmbeddedChannel ch) {
                    ch.pipeline().addLast(spliter);
                    ch.pipeline().addLast(new
                            StringDecoder(StandardCharsets.UTF_8));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };
            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 1; j <= 100; j++) {
                ByteBuf buf = Unpooled.buffer();
                String s = j + "次发送->" + content;
                byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
                buf.writeInt(bytes.length);
                buf.writeChar(VERSION);
                buf.writeBytes(bytes);
                channel.writeInbound(buf);
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
