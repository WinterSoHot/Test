package cn.dx.common.state;

/**
 * 状态接口，需要使用状态机校验能力的模型状态，需要使用枚举类实现接口
 * @author gudongxian
 * @date 2021/7/22
 */
public interface Status extends Code{
    String EDEN_STATUS = "(*)";

    /**
     * 是否起始状态
     * @return
     */
    default boolean isStart() {return false;}

    /**
     * 是否结束状态
     * @return
     */
    default boolean isEnd() {return false;}

    /**
     * 检验状态转移是否合法
     * @param eventCode
     * @param target
     */
    default void assertTransit(EventCode eventCode, Status target){
        State state = StateFactory.getState(target.getClass());
        state.assertTransit(this, eventCode, target);
    }
}
