package cn.dx.netty.basic.tomcat.servlet;

import cn.dx.netty.basic.tomcat.GPRequest;
import cn.dx.netty.basic.tomcat.GPResponse;
import cn.dx.netty.basic.tomcat.GPServlet;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public class FirstServlet extends GPServlet {

    @Override
    protected void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is first Servlet");
    }

    @Override
    protected void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }
}
