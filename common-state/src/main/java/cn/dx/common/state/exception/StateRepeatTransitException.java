package cn.dx.common.state.exception;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateRepeatTransitException extends StateException {
    private static final long serialVersionUID = 5384750274086789270L;

    public StateRepeatTransitException() {
    }

    public StateRepeatTransitException(String message) {
        super(message);
    }

    public StateRepeatTransitException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateRepeatTransitException(Throwable cause) {
        super(cause);
    }
}
