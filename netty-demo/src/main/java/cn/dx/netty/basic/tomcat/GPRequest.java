package cn.dx.netty.basic.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class GPRequest {
    private String method;
    private String url;

    public GPRequest(InputStream is) {
        // 解析HTTP内容
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = is.read(buffer)) > 0) {
                content = new String(buffer, 0, len);
            }
            System.out.println(content);
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
