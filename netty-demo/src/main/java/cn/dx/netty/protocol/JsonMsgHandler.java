package cn.dx.netty.protocol;

import cn.dx.netty.util.JsonMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/27
 */
public class JsonMsgHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String json = (String) msg;
        JsonMsg jsonMsg = JsonMsg.parseFromJson(json);
        System.out.println("收到JSON数据包：" + jsonMsg);
    }
}
