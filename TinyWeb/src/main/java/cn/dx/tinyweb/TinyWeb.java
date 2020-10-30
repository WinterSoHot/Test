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
 * 整个Web对象
 * 由控制器和过滤器组合而成
 *
 * @author gudongxian
 */
public class TinyWeb {
    /**
     * 控制器列表：通过Path和Controller进行映射
     */
    private Map<String, Controller> controllers;
    /**
     * 过滤器列表
     */
    private List<Filter> filters;

    public TinyWeb(Map<String, Controller> controllers, List<Filter> filters) {
        this.controllers = controllers;
        this.filters = filters;
    }

    /**
     * 处理请求：先进行过滤，在根据路径执行响应的控制器
     *
     * @param httpRequest 请求对象
     * @return 响应对象
     */
    public HttpResponse handleRequest(HttpRequest httpRequest) {

        HttpRequest currentRequest = httpRequest;
        for (Filter filter : filters) {
            currentRequest = filter.doFilter(currentRequest);
        }

        Controller controller = controllers.get(currentRequest.getPath());

        if (null == controller) {
            return null;
        }

        return controller.handleRequest(currentRequest);
    }
}
