package cn.dx.common.state;

/**
 * 状态机
 * @author gudongxian
 * @date 2021/7/22
 */
public interface State {

    /**
     * 校验状态转移是否合法
     * @param source
     * @param eventCode
     * @param target
     */
    void assertTransit(Status source, EventCode eventCode, Status target);

    boolean isStartStatus(Status status);

    boolean isEndStatus(Status status);
}
