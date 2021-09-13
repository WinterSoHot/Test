package cn.dx.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/24
 */
public class IntegerProcessHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer i = (Integer) msg;
        System.out.println("Print A Integer:" + i);
        super.channelRead(ctx, msg);
    }
}
