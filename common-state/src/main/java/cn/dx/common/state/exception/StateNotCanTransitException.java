package cn.dx.common.state.exception;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateNotCanTransitException extends StateException {
    private static final long serialVersionUID = 5384750274086789270L;

    public StateNotCanTransitException() {
    }

    public StateNotCanTransitException(String message) {
        super(message);
    }

    public StateNotCanTransitException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateNotCanTransitException(Throwable cause) {
        super(cause);
    }
}
