package cn.dx.common.txevent.dal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Setter
@Getter
public class TxEventMonitorEntity {
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 汇总
     */
    private Integer count;

    /**
     * 业务身份
     */
    private String bizCode;

    public TxEventMonitorEntity append(TxEventMonitorEntity entity) {
        if (this.bizCode.equals(entity.getBizCode()) && this.eventType.equals(entity.getEventType())) {
            this.count += entity.getCount();
        }
        return this;
    }

    public String getGroupKey() {
        return this.bizCode + "#" + this.eventType;
    }
}
