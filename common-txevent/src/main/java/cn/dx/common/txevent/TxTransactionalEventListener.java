package cn.dx.common.txevent;

import cn.dx.common.txevent.model.CommonTransactionEvent;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public interface TxTransactionalEventListener {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void transactionalEventListener(CommonTransactionEvent event);
}
