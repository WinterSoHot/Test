package cn.dx.netty.basic.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class GPTomcat {
    private Map<String, GPServlet> servletMap = new HashMap<>();
    private int port = 8080;
    private ServerSocket server;

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            Properties webProperties = new Properties();
            webProperties.load(fis);
            for (Object k : webProperties.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(servletName + ".className");
                    GPServlet servlet = (GPServlet) Class.forName(className).newInstance();
                    servletMap.put(url, servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        try {
            server = new ServerSocket(this.port);
            System.out.println("GPTomcat已启动， 端口为:" + port);
            while (true) {
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        GPRequest request = new GPRequest(is);
        GPResponse response = new GPResponse(os);

        String url = request.getUrl();

        if (servletMap.containsKey(url)) {
            servletMap.get(url).service(request, response);
        } else {
            response.write("404. Not Found");
        }
        os.flush();
        os.close();
        is.close();
        client.close();
    }
}
