import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/5
 */
public class UDPServer {
    public static void main(String[] args) {
        try {

            DatagramSocket ds = new DatagramSocket(6666); // 监听指定端口
            for (; ; ) { // 无限循环
                // 数据缓冲区:
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                ds.receive(packet);
                String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
                // 发送数据:
                byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
                packet.setData(data);
                ds.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
