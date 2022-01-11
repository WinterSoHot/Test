package cn.dx.async.method.invocation;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author gudongxian
 * @date 2022/1/10
 */

@Slf4j
public class App {

    private static final String ROCKET_LAUNCH_LOG_PATTERN = "Space rocket <%s> launched successfully";

    /**
     * Program entry point.
     */

    public static void main(String[] args) throws Exception {
        // construct a new executor that will run async tasks
        AsyncExecutor executor = new ThreadAsyncExecutor();

        // start few async tasks with varying processing times, two last with callback handlers
        final AsyncResult<Integer> asyncResult1 = executor.startProcess(lazyVal(10, 500));
        final AsyncResult<String> asyncResult2 = executor.startProcess(lazyVal("test", 300));
        final AsyncResult<Long> asyncResult3 = executor.startProcess(lazyVal(50L, 700));
        final AsyncResult<Integer> asyncResult4 = executor.startProcess(lazyVal(20, 400),
                callback("Deploying lunar rover"));
        final AsyncResult<String> asyncResult5 =
                executor.startProcess(lazyVal("callback", 600), callback("Deploying lunar rover"));

        // emulate processing in the current thread while async tasks are running in their own threads
        Thread.sleep(350); // Oh boy, we are working hard here
        log("Mission command is sipping coffee");

        // wait for completion of the tasks
        final Integer result1 = executor.endProcess(asyncResult1);
        final String result2 = executor.endProcess(asyncResult2);
        final Long result3 = executor.endProcess(asyncResult3);
        asyncResult4.await();
        asyncResult5.await();

        // log the results of the tasks, callbacks log immediately when complete
        log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result1));
        log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result2));
        log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result3));
    }

    /**
     * Creates a callable that lazily evaluates to given value with artificial delay.
     *
     * @param value       value to evaluate
     * @param delayMillis artificial delay in milliseconds
     * @return new callable for lazy evaluation
     */
    private static <T> Callable<T> lazyVal(T value, long delayMillis) {
        return () -> {
            Thread.sleep(delayMillis);
            log(String.format(ROCKET_LAUNCH_LOG_PATTERN, value));
            return value;
        };
    }

    /**
     * Creates a simple callback that logs the complete status of the async result.
     *
     * @param name callback name
     * @return new async callback
     */
    private static <T> AsyncCallback<T> callback(String name) {
        return (value, ex) -> {
            if (ex.isPresent()) {
                log(name + " failed: " + ex.map(Exception::getMessage).orElse(""));
            } else {
                log(name + " <" + value + ">");
            }
        };
    }

    private static void log(String msg) {
        log.info(msg);
    }
}
