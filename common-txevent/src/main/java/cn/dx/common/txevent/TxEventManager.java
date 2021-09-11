package cn.dx.common.txevent;

import cn.dx.common.txevent.model.TxEvent;

/**
 * 事务事件管理器
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public interface TxEventManager {
    /**
     * 发送事件
     * @param txEvent 事件模型
     */
    void sendEvent(TxEvent txEvent);
}
