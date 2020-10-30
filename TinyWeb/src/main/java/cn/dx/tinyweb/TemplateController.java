/***
 * Excerpted from "Functional Programming Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/mbfpp for more book information.
 ***/
package cn.dx.tinyweb;

import java.util.List;
import java.util.Map;

/**
 * 模板控制器：成员对象View
 *
 * @author gudongxian
 */
public abstract class TemplateController implements Controller {
    /**
     * 请求对应的页面
     */
    private View view;

    public TemplateController(View view) {
        this.view = view;
    }

    /**
     * 处理请求
     *
     * @param request
     * @return
     */
    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        Integer responseCode = 200;
        String responseBody = "";

        try {
            // 用户逻辑
            Map<String, List<String>> model = doRequest(request);
            // 渲染
            responseBody = view.render(model);
        } catch (ControllerException e) {
            responseCode = e.getStatusCode();
        } catch (RenderingException e) {
            responseCode = 500;
            responseBody = "Exception while rendering.";
        } catch (Exception e) {
            responseCode = 500;
        }
        // 构建响应请求
        return HttpResponse.Builder.newBuilder().body(responseBody)
                .responseCode(responseCode).build();
    }

    /**
     * 上层实现处理请求，返回结果
     *
     * @param request 请求
     * @return 处理结果
     */
    protected abstract Map<String, List<String>> doRequest(HttpRequest request);
}
