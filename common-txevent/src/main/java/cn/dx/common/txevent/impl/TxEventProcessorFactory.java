package cn.dx.common.txevent.impl;

import cn.dx.common.txevent.TxEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Component
public class TxEventProcessorFactory {
    private Map<String, TxEventProcessor> processorMap = new HashMap<>();

    @Autowired
    public void setTxEventProcessor(List<TxEventProcessor> processors) {
        processors.forEach(
                processor -> processor.supportEventTypes()
                        .forEach(t -> processorMap.put(t, processor)));
    }

    public TxEventProcessor get(String eventType) {
        return processorMap.get(eventType);
    }
}
