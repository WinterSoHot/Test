import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/5
 */
public class UDPClient {

    public String sendDataByUDP(String host, Integer port, String content) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            ds.setSoTimeout(60000);
            ds.connect(InetAddress.getByName(host), port);
            byte[] data = content.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            ds.send(packet);
            byte[] buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println(resp);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null && ds.isConnected()) {
                ds.disconnect();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            ds.setSoTimeout(60000);
            ds.connect(InetAddress.getByName("localhost"), 6666);
            byte[] data = "Hello".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            ds.send(packet);
            byte[] buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println(resp);
            ds.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
