package cn.dx.netty.protocol;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/27
 */
public class MsgProtosTest {
    public MsgProtos.Msg buildMsg() {
        return MsgProtos.Msg.newBuilder().setId(1000).setContent("哈哈哈").build();
    }

    /**
     * 序列化和反序列化
     */
    @Test
    public void serAndDesr1() throws IOException {
        MsgProtos.Msg msg = buildMsg();
        byte[] data = msg.toByteArray();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(data);
        data = outputStream.toByteArray();
        MsgProtos.Msg inMsg = MsgProtos.Msg.parseFrom(data);
        System.out.println("id =" + inMsg.getId());
        System.out.println("content =" + inMsg.getContent());
    }

    //第2种方式:序列化 serialization &反序列化 Deserialization
    @Test
    public void serAndDesr2() throws IOException {
        MsgProtos.Msg message = buildMsg();
        //序列化到二进制码流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());
        //从二进码流反序列化成Protobuf对象
        MsgProtos.Msg inMsg = MsgProtos.Msg.parseFrom(inputStream);
        System.out.println("id =" + inMsg.getId());
        System.out.println("content =" + inMsg.getContent());
    }

    //第3种方式:序列化 serialization &反序列化 Deserialization
    //带字节长度：[字节长度][字节数据],解决粘包/半包问题
    @Test
    public void serAndDesr3() throws IOException {
        MsgProtos.Msg message = buildMsg();
        //序列化到二进制码流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeDelimitedTo(outputStream);
        ByteArrayInputStream inputStream
                = new ByteArrayInputStream(outputStream.toByteArray());
        //从二进码流反序列化成Protobuf对象
        MsgProtos.Msg inMsg = MsgProtos.Msg.parseDelimitedFrom(inputStream);
        System.out.println("id =" + inMsg.getId());
        System.out.println("content =" + inMsg.getContent());
    }

}