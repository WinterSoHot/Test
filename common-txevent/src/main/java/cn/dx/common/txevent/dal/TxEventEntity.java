package cn.dx.common.txevent.dal;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 事务事件实体
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Getter
@Setter
public class TxEventEntity {
    private Long id;
    /**
     * 用户id，分库分表键
     */
    private Long userId;
    /**
     * 事件id，唯一键
     */
    private String eventId;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件体
     */
    private String eventBody;
    /**
     * 事件体类名称
     */
    private String eventBodyClassName;
    /**
     * 属性
     */
    private String properties;
    /**
     * 重试次数
     */
    private Integer retryCount;
    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;
    /**
     * 状态
     * @see cn.dx.common.txevent.model.TxEventStatusEnum
     */
    private String status;
    /**
     * 错误消息
     */
    private String errorMessage;
    /**
     * 执行时间
     */
    private Date gmtExecute;
    private Date gmtCreate;
    private Date gmtModified;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
