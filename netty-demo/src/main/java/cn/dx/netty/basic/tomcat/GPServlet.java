package cn.dx.netty.basic.tomcat;

/**
 * @author gudongxian
 * @date 2022/1/13
 */
public abstract class GPServlet {
    public void service(GPRequest request, GPResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getUrl())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(GPRequest request, GPResponse response) throws Exception;

    protected abstract void doGet(GPRequest request, GPResponse response) throws Exception;
}
