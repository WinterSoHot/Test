package cn.dx.common.txevent.dao;

import cn.dx.common.txevent.dal.TxEventEntity;

import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public interface TxEventEntityDao {
    void insert(TxEventEntity entity);

    void update(TxEventEntity entity);

    List<TxEventEntity> rollerQuery(Map params);

    List<TxEventEntity> rollerQuery4Monitor(Map params);

    List<TxEventEntity> rollerGroupKey(Map params);

    void delete(TxEventEntity entity);

    List<TxEventEntity> queryList(Map params);

    TxEventEntity queryByUserIdAndEventIdAndEventType(Long userId, String eventId, String eventType);

    void deleteByUserIdAndEventIdAndEventType(Long userId, String eventId, String eventType);
}
