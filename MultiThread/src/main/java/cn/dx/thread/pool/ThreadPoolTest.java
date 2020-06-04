package cn.dx.thread.pool;

import java.util.concurrent.*;

/**
 * @author dongxian
 * @version 1.0
 * @className ThreadPoolTest
 * @description TODO
 * @date 20-5-18 上午8:26
 **/
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(6);
        Runnable target = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "的i的值：" + i);
            }
        };
        pool.submit(target);
        pool.submit(target);
        pool.shutdown();

        Callable<String> call = () -> {
            return "This is callable";
        };
        ScheduledExecutorService scheduledPool = Executors.newSingleThreadScheduledExecutor();
        scheduledPool.schedule(target, 1, TimeUnit.SECONDS);
        ScheduledFuture<String> callFuture = scheduledPool.schedule(call, 1, TimeUnit.SECONDS);
        System.out.println(callFuture.get());
        scheduledPool.shutdown();

        ExecutorService workStealingPool = Executors.newWorkStealingPool(4);
        workStealingPool.shutdown();
    }
}
