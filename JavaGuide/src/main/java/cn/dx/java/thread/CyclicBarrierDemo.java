package cn.dx.java.thread;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/5
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 6, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Runnable down = new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println(Thread.currentThread().getName() + " 执行开始时间:" + new Date());
                    Thread.sleep(1000);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 继续执行时间:" + new Date());
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            poolExecutor.execute(down);
        }
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {
        }
    }
}
