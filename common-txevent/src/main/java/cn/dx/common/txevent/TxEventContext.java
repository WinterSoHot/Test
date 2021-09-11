package cn.dx.common.txevent;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 事务事件上下文
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Getter
@Setter
public class TxEventContext {
    /**
     * 重试次数
     */
    private Integer retryCount;
    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;
    /**
     * 执行事件
     */
    private Date gmtExecute;
    private Date gmtCreate;
    private Date gmtModified;
}
