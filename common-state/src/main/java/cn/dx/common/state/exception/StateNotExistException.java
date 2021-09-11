package cn.dx.common.state.exception;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateNotExistException extends StateException {
    private static final long serialVersionUID = 5384750274086789270L;

    public StateNotExistException() {
    }

    public StateNotExistException(String message) {
        super(message);
    }

    public StateNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateNotExistException(Throwable cause) {
        super(cause);
    }
}
