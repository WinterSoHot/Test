package cn.dx.netty.basic.netty.tomcat;

import cn.dx.netty.basic.tomcat.GPServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class GPTomcat {
    private Map<String, GPServlet> servletMap = new HashMap<>();
    private int port = 8080;
    private ServerSocket server;

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            Properties webProperties = new Properties();
            webProperties.load(fis);
            for (Object k : webProperties.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(servletName + ".className");
                    GPServlet servlet = (GPServlet) Class.forName(className).newInstance();
                    servletMap.put(url, servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            // 1. 创建对象
            ServerBootstrap server = new ServerBootstrap();

            // 2. 配置参数
            server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 编码器，解码器
                    // 输入输出的对象序列化
                    // 有顺序要求
                    ch.pipeline().addLast(new HttpResponseEncoder());
                    ch.pipeline().addLast(new HttpRequestDecoder());

                    // 业务逻辑
                    ch.pipeline().addLast(new GPTomcatHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = server.bind(port).sync();
            System.out.println("GPTomcat 已经启动， 监听的端口是：" + port);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public class GPTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest) {
                HttpRequest req = (HttpRequest) msg;
                GPRequest request = new GPRequest(ctx, req);
                GPResponse response = new GPResponse(ctx, req);
                // 业务处理
                String url = request.getUrl();
                if (servletMap.containsKey(url)) {
                    servletMap.get(url).service(request, response);
                } else {
                    response.write("404. Not Found");
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        }
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
