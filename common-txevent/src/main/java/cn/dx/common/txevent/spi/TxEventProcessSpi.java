package cn.dx.common.txevent.spi;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public interface TxEventProcessSpi {
    void process(Long userId, String eventType, String eventId);

    boolean syncProcess(Long userId, String eventType, String eventId);

    /**
     * userId只做路由
     * eventId做执行
     * @param userId 用户ID
     * @param eventId 事件ID
     */
    void processByEventId(Long userId, String eventId);

    /**
     * 执行userId下的所有eventType
     * @param userId 用户Id
     * @param eventType 事件类型
     */
    void processByUserIdAndEventType(Long userId, String eventType);
}
