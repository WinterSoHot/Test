package cn.dx.balking;

import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @date 2022/2/9
 */
public interface DelayProvider {
    void executeAfterDelay(long interval, TimeUnit timeUnit, Runnable task);
}
