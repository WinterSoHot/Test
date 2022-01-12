package cn.dx.netty.basic.source;

import sun.nio.ch.IOUtil;
import sun.nio.ch.SelChImpl;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class SourceCode {
    public static void main(String[] args) throws IOException {
        // Selector 创建
        // native method ->  Pipe -> SelectorProvider -> Selector
        Selector selector = Selector.open();
        // native 管道的创建，包括文件描述符的创建
        Pipe pipe = Pipe.open();
        int fdVal = ((SelChImpl) pipe.source()).getFDVal();
        System.out.println(fdVal);

        // native method -> SelectorProvider -> ServerSocketChannel
        /**
         * ServerSocketChannelImpl(SelectorProvider var1) throws IOException {
         *     super(var1);
         *     this.fd = Net.serverSocket(true);
         *     this.fdVal = IOUtil.fdVal(this.fd);
         *     this.state = 0;
         * }
         */
        ServerSocketChannel server = ServerSocketChannel.open();

        // Selector readFD writeFD
        // 关联 Channel 和 Selector 中的 FD
        server.register(selector, SelectionKey.OP_ACCEPT);
        // 调用poll()阻塞，直到关注的FD有数据
        selector.select();
        // 通过玩 writeFD 发送一个byte来唤醒
        selector.wakeup();
    }
}
