package cn.dx.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * 半包问题的实践案例
 * <p>
 * 查看服务器回显的结果
 * <p>
 * （1）读到一个完整的客户端输入ByteBuf。
 * （2）读到多个客户端的ByteBuf输入，但是“粘”在了一起。
 * （3）读到部分ByteBuf的内容，并且有乱码。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/23
 */
public class NettyEchoDumpSendClient {
    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyEchoDumpSendClient(String ip, int port) {
        this.serverPort = port;
        this.serverIp = ip;
    }

    public void runClient() {
        //创建reactor 线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        try {
            //1 设置reactor 线程组
            b.group(workerLoopGroup);
            //2 设置nio类型的channel
            b.channel(NioSocketChannel.class);
            //3 设置监听端口
            b.remoteAddress(serverIp, serverPort);
            //4 设置通道的参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //5 装配子通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理子通道channel中的Handler
                    // 向子channel流水线添加一个handler处理器
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture futureListener) ->
            {
                if (futureListener.isSuccess()) {
                    System.out.println("EchoClient客户端连接成功!");
                } else {
                    System.out.println("EchoClient客户端连接失败!");
                }

            });

            // 阻塞,直到连接完成
            f.sync();
            Channel channel = f.channel();
            String content = "疯狂创客圈：高性能学习者社群!";
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < 1000; i++) {
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyEchoDumpSendClient client = new NettyEchoDumpSendClient("127.0.0.1", 8089);
        client.runClient();
    }
}
