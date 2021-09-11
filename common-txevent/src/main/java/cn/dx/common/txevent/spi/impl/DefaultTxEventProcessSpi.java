package cn.dx.common.txevent.spi.impl;

import cn.dx.common.txevent.spi.TxEventProcessSpi;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public class DefaultTxEventProcessSpi  implements TxEventProcessSpi {
    @Override
    public void process(Long userId, String eventType, String eventId) {

    }

    @Override
    public boolean syncProcess(Long userId, String eventType, String eventId) {
        return false;
    }

    @Override
    public void processByEventId(Long userId, String eventId) {

    }

    @Override
    public void processByUserIdAndEventType(Long userId, String eventType) {

    }
}
