package cn.dx.common.state;

import org.springframework.core.io.Resource;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public interface StateFileParser {
    /**
     * 将状态机puml文件解析成状态机对象
     * @param statusClass 状态机类
     * @param resource 状态机puml文件资源
     * @return 状态机对象
     */
    State parserFile(Class<? extends Status> statusClass, Resource resource);
}
