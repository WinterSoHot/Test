package cn.dx.netty.gateway;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpProxyHandler extends ChannelInboundHandlerAdapter {
    private String proxyServerURL;

    public HttpProxyHandler(String proxyServerURL) {
        this.proxyServerURL = proxyServerURL;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest request = (FullHttpRequest) msg;
            proxyHttpRequest(request, ctx.channel());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 发起请求
     *
     * @param request      客户端请求
     * @param replyChannel 客户端管道
     */
    private void proxyHttpRequest(FullHttpRequest request, Channel replyChannel) throws InterruptedException {
        // 修改请求信息
        FullHttpRequest proxyRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, request.uri());
        proxyRequest.headers().add(request.headers().copy());
        proxyRequest.content().writeBytes(request.content());
        Bootstrap b = new Bootstrap();
        b.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new HttpClientCodec());
                        ch.pipeline().addLast(new HttpObjectAggregator(65536));
                        ch.pipeline().addLast(new HttpContentDecompressor());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                // 写入HTTP请求报文
                                ctx.writeAndFlush(proxyRequest);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                ctx.close();
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                if (msg instanceof FullHttpResponse) {
                                    FullHttpResponse response = (FullHttpResponse) msg;
                                    ByteBuf content = response.content();
                                    LOGGER.info("直接请求返回的内容: {}", content.toString(StandardCharsets.UTF_8));
                                    // 回写客户端管道
                                    replyChannel.writeAndFlush(response);
                                    ctx.close();
                                }
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                if (replyChannel.isActive()) {
                                    replyChannel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                                }
                            }
                        });
                    }
                });
        String[] address = proxyServerURL.split(":");
        String hostname = address[0];
        int port = Integer.parseInt(address[1]);
        ChannelFuture future = b.connect(new InetSocketAddress(hostname, port)).sync();
        future.channel().closeFuture().sync();
    }
}
