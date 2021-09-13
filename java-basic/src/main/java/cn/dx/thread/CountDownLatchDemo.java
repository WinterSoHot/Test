package cn.dx.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/27
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 6, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        CountDownLatch cdl = new CountDownLatch(5);

        Runnable down = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务执行一次");
                cdl.countDown();
            }
        };
        Runnable last = new Runnable() {
            @Override
            public void run() {
                if (cdl.getCount() > 0) {
                    try {
                        // 让当前线程等计时结束后再执行
                        cdl.await();
                        System.out.println("执行最后一个线程");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        poolExecutor.execute(last);
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(down);
        }
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {
        }
    }
}
