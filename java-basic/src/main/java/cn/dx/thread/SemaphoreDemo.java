package cn.dx.thread;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/27
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 6, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        // 实现共享访问 permits 几个线程共享
        Semaphore signals = new Semaphore(3);

        Runnable down = new Runnable() {
            @Override
            public void run() {
                try {
                    signals.acquire();
                    System.out.println(Thread.currentThread().getName() + " 执行开始时间:" + new Date());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    signals.release();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(down);
        }
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {
        }
    }
}
