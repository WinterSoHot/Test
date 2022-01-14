package cn.dx.netty.basic.netty.tomcat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class GPResponse extends cn.dx.netty.basic.tomcat.GPResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public GPResponse(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    @Override
    public void write(String content) throws IOException {
        try {
            if (content == null || content.length() == 0) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "text/html");
            ctx.write(response);
        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
