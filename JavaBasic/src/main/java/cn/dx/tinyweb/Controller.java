/***
 * Excerpted from "Functional Programming Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/mbfpp for more book information.
 ***/
package cn.dx.tinyweb;

/**
 * 控制器
 *
 * @author gudongxian
 */
public interface Controller {
    /**
     * 处理请求，返回响应
     *
     * @param httpRequest 请求对象
     * @return 响应对象
     */
    HttpResponse handleRequest(HttpRequest httpRequest);
}
