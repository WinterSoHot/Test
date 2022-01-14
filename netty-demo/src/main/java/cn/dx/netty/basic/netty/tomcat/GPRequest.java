package cn.dx.netty.basic.netty.tomcat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class GPRequest extends cn.dx.netty.basic.tomcat.GPRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    @Override
    public String getMethod() {
        return request.method().name();
    }

    @Override
    public String getUrl() {
        return request.uri();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> parameters = getParameters();
        List<String> param = parameters.get(name);
        if (param == null) {
            return null;
        } else {
            return param.get(0);
        }
    }
}
