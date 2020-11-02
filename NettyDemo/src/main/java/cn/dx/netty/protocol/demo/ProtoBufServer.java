package cn.dx.netty.protocol.demo;

import cn.dx.netty.protocol.MsgProtos;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/2
 */
public class ProtoBufServer {
    private final int port;
    ServerBootstrap b = new ServerBootstrap();

    public ProtoBufServer(int port) {
        this.port = port;
    }

    public void runServer() {
        // 创建反应器线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        // 设置
        try {
            // 设置reactor线程组
            b.group(bossLoopGroup, workerLoopGroup);
            // 设置通道类型
            b.channel(NioServerSocketChannel.class);
            //3 设置监听端口
            b.localAddress(port);
            //4 设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //5 装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    ch.pipeline().addLast(new ProtobufDecoder(MsgProtos.Msg.getDefaultInstance()));
                    ch.pipeline().addLast(new ProtobufBussinessDecoder());
                }
            });
            // 6 开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind().sync();
            System.out.println(" 服务器启动成功，监听端口: " +
                    channelFuture.channel().localAddress());

            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ProtobufBussinessDecoder extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            MsgProtos.Msg protoMsg = (MsgProtos.Msg) msg;
            System.out.println("收到MsgProtos.Msg数据包");
            System.out.println("protoMsg.getId():=" + protoMsg.getId());
            System.out.println("protoMsg.getContent():=" + protoMsg.getContent());
        }
    }

    public static void main(String[] args) {
        ProtoBufServer server = new ProtoBufServer(8089);
        server.runServer();
    }
}
