package cn.dx.netty.protocol.demo;

import cn.dx.netty.protocol.MsgProtos;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/23
 */
public class ProtoBufClient {
    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public ProtoBufClient(String ip, int port) {
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
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
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
            for (int i = 0; i < 1000; i++) {
                channel.writeAndFlush(this.build(i, 1 + " -> " + "content"));
                System.out.println("发送个数： " + i);
            }
            channel.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }
    }

    public MsgProtos.Msg build(int id, String content) {
        return MsgProtos.Msg.newBuilder().setId(id).setContent(content).build();
    }

    public static void main(String[] args) {
        ProtoBufClient client = new ProtoBufClient("127.0.0.1", 8089);
        client.runClient();
    }
}
