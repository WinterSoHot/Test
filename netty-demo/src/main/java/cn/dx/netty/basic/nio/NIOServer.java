package cn.dx.netty.basic.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author gudongxian
 * @date 2022/1/12
 */
public class NIOServer {

    private int port;
    private Selector selector;
    private ByteBuffer buffer;

    public NIOServer() {
        this.port = 8600;
        try {
            this.selector = getSelector();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = ByteBuffer.allocate(1024);
    }

    private Selector getSelector() throws IOException {
        Selector sel = Selector.open();

        // 创建通道，配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        // 绑定端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);

        // 注册事件
        server.register(sel, SelectionKey.OP_ACCEPT);
        return sel;
    }

    public void listen() {
        System.out.println("listen on " + port);
        try {
            while (true) {
                // 调用阻塞，直到至少有一个事件发生
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理不同的事件
     *
     * @param key 事件信息
     * @throws IOException
     */
    private void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
                sKey.attach(content);
            } else {
                channel.close();
            }
            buffer.clear();
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            ByteBuffer block = ByteBuffer.wrap(("输出内容： " + content).getBytes());
            if (content != null) {
                channel.write(block);
            } else {
                channel.close();
            }
        }
    }

}
