package cn.dx.async.method.invocation;

import java.util.concurrent.ExecutionException;

/**
 * @author gudongxian
 * @date 2022/1/10
 */
public interface AsyncResult<T> {

    boolean isCompleted();

    T getValue() throws ExecutionException;

    void await() throws InterruptedException;

}
