package cn.dx.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class IntegerAddDecoder extends ReplayingDecoder<IntegerAddDecoder.Status> {
    /**
     * 标识解码过程状态
     */
    enum Status {
        /**
         * 状态1
         */
        PARSE_1,
        /**
         * 状态2
         */
        PARSE_2
    }

    private int first;
    private int second;

    public IntegerAddDecoder() {
        // 构造函数，初始化父类的state状态，表示当前阶段
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()) {
            case PARSE_1:
                // 从装饰器ByteBuf中读取数据
                first = byteBuf.readInt();
                //第一步解析成功
                //进入第二步，并设置“读断点指针”为当前读取位置
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                second = byteBuf.readInt();
                Integer sum = first + second;
                list.add(sum);
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }

    }


}
