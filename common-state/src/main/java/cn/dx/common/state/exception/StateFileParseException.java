package cn.dx.common.state.exception;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateFileParseException extends StateException {
    private static final long serialVersionUID = 5384750274086789270L;

    public StateFileParseException() {
    }

    public StateFileParseException(String message) {
        super(message);
    }

    public StateFileParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateFileParseException(Throwable cause) {
        super(cause);
    }
}
