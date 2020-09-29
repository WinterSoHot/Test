package cn.dx.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/17
 */
public class ReactorModel {
    static class EchoServerReactor implements Runnable {
        Selector selector;
        ServerSocketChannel serverSocket;

        public EchoServerReactor() throws IOException {
            SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new AcceptorHandler());
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selected.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey sk = iterator.next();
                        dispatch(sk);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void dispatch(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment();
            if (handler != null) {
                handler.run();
            }
        }

        class AcceptorHandler implements Runnable {

            @Override
            public void run() {
                SocketChannel channel = null;
                try {
                    channel = serverSocket.accept();
                    if (channel != null) {
                        new EchoHandler(selector, channel);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class EchoHandler implements Runnable {
        final SocketChannel channel;
        final SelectionKey sk;
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        static final int RECIEVING = 0, SENDING = 1;
        int state = RECIEVING;

        EchoHandler(Selector selector, SocketChannel c) throws IOException {
            channel = c;
            c.configureBlocking(false);
            //取得选择键，再设置感兴趣的IO事件
            sk = channel.register(selector, 0);
            //将Handler自身作为选择键的附件
            sk.attach(this);
            //注册Read就绪事件
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {

        }
    }
}
