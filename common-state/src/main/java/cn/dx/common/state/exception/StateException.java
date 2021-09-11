package cn.dx.common.state.exception;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateException extends RuntimeException{
    private static final long serialVersionUID = 5384750274086789270L;

    public StateException() {
    }

    public StateException(String message) {
        super(message);
    }

    public StateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateException(Throwable cause) {
        super(cause);
    }
}
