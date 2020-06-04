package cn.dx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetHelper {
    public static boolean ping(String ipStr) {

        try {
            InetAddress inetAddress = InetAddress.getByName(ipStr);
            if (inetAddress.isReachable(5000)) {
                return true;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
