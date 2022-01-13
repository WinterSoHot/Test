package cn.dx.netty.basic.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class GPResponse {
    private OutputStream output;

    public GPResponse(OutputStream output) {
        this.output = output;
    }

    public void write(String content) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP1.1 200 OK\n").append("Content-Type: text/html;\n").append("\r\n").append(content);
        this.output.write(sb.toString().getBytes());
    }
}
