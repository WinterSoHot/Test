package cn.dx.common.txevent.model;

import cn.dx.common.txevent.TxEventContext;
import cn.dx.common.txevent.dal.TxEventEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Getter
@Setter
public class CommonTransactionEvent extends ApplicationEvent {
    private TxEvent txEvent;
    private TxEventEntity entity;
    public CommonTransactionEvent(TxEvent txEvent,TxEventEntity eventEntity) {
        super(txEvent);
        this.txEvent = txEvent;
        this.entity = eventEntity;
    }

    public TxEventContext getTxEventContext(){
        TxEventContext context = new TxEventContext();
        context.setGmtCreate(entity.getGmtCreate());
        context.setGmtExecute(entity.getGmtExecute());
        context.setGmtModified(entity.getGmtModified());
        context.setRetryCount(entity.getRetryCount());
        context.setMaxRetryCount(entity.getMaxRetryCount());
        return context;
    }
}
