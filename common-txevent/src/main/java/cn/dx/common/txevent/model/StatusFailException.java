package cn.dx.common.txevent.model;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public class StatusFailException extends RuntimeException{
    public StatusFailException(String message) {
        super(message);
    }
}
