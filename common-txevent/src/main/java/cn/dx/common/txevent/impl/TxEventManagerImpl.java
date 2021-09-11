package cn.dx.common.txevent.impl;

import cn.dx.common.txevent.TxEventManager;
import cn.dx.common.txevent.TxEventProcessor;
import cn.dx.common.txevent.TxTransactionalEventListener;
import cn.dx.common.txevent.dal.TxEventEntity;
import cn.dx.common.txevent.dao.TxEventEntityDao;
import cn.dx.common.txevent.model.CommonTransactionEvent;
import cn.dx.common.txevent.model.TxEvent;
import cn.dx.common.txevent.model.TxEventStatusEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Component
public class TxEventManagerImpl implements TxEventManager, ApplicationEventPublisherAware, TxTransactionalEventListener {

    @Autowired
    private TxEventEntityDao dao;

    private ApplicationEventPublisher publisher;

    @Override
    public void sendEvent(TxEvent txEvent) {
        long timeStamp = txEvent.getDelayMilliSeconds() + System.currentTimeMillis();
        TxEventEntity entity = buildTxEventEntity(txEvent, new Date(timeStamp));
        dao.insert(entity);
        CommonTransactionEvent event = new CommonTransactionEvent(txEvent, entity);
        publisher.publishEvent(event);
    }

    private TxEventEntity buildTxEventEntity(TxEvent txEvent, Date expectExecuteTime) {
        TxEventEntity entity = new TxEventEntity();
        entity.setGmtExecute(expectExecuteTime);
        entity.setUserId(txEvent.getUserId());
        entity.setRetryCount(0);
        entity.setEventType(txEvent.getEventType());
        entity.setEventId(txEvent.getEventId());

        Map<String, String> properties = new HashMap<>(txEvent.getProperties());

        entity.setProperties(JSON.toJSONString(properties));
        entity.setStatus(TxEventStatusEnum.INIT.name());
        entity.setEventBodyClassName(txEvent.getEventBody().getClass().getName());
        return entity;
    }

    @Autowired
    TxEventProcessorFactory processorFactory;

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void transactionalEventListener(CommonTransactionEvent event) {
        TxEvent txEvent = event.getTxEvent();
        TxEventProcessor processor = processorFactory.get(event.getEntity().getEventType());
        Assert.notNull(processor, "no processor for eventType " + event.getEntity().getEventType());
        // 构建异步执行
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
