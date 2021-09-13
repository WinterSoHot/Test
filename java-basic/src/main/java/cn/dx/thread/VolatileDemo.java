package cn.dx.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/16
 */
public class VolatileDemo {
    public static volatile int state = 0;


    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 20, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 1000; i++) {
            poolExecutor.execute(() -> {
                state++;
            });
        }
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {

        }
        System.out.println(state);
    }
}
