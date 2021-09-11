package cn.dx.common.txevent;

import cn.dx.common.txevent.model.TxEvent;

import java.util.List;

/**
 * 事务事件处理器
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public interface TxEventProcessor {
    /**
     * 事件处理
     * @param txEvent 事件模型
     * @param txEventContext 事件上下文
     */
    void process(TxEvent txEvent,TxEventContext txEventContext);

    /**
     * 支持的事件类型
     * @return
     */
    List<String> supportEventTypes();
}
