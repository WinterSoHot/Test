package cn.dx.async.method.invocation;

import java.util.Optional;

/**
 * @author gudongxian
 * @date 2022/1/10
 */
public interface AsyncCallback<T> {
    void onComplete(T value, Optional<Exception> ex);
}
