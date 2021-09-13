package cn.dx.tinyweb;

import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/27
 */
public interface View {
    /**
     * 渲染模型
     *
     * @param model 模型
     * @return 返回渲染结果
     */
    public String render(Map<String, List<String>> model);
}
