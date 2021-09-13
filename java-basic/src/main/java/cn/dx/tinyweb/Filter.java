package cn.dx.tinyweb;


/**
 * 过滤器
 *
 * @author gudongxian
 */
public interface Filter {
    /**
     * 在Controller处理之前进行过滤操作
     *
     * @param request 请求对象
     * @return 返回处理后的请求对象
     */
    HttpRequest doFilter(HttpRequest request);
}