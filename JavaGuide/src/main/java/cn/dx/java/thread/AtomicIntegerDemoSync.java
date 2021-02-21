package cn.dx.java.thread;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class AtomicIntegerDemoSync {
    private final static AtomicInteger count = new AtomicInteger(0);

    public static void increase() {
        count.incrementAndGet();
    }

    public static int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        Field valueOffsetField = null;
        try {
            valueOffsetField = AtomicInteger.class.getDeclaredField("valueOffset");
            valueOffsetField.setAccessible(true);
            System.out.println("valueOffset = " + valueOffsetField.get(count));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Runnable instanceGet = () -> {
            for (int i = 0; i < 10000; i++) {
                increase();
            }
        };
        Runnable instancePut = () -> {
            for (int i = 0; i < 10000; i++) {
                increase();
            }
        };
        ExecutorService threadPool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                r -> new Thread(r, "blockQueue-main"));
        threadPool.execute(instanceGet);
        threadPool.execute(instancePut);
        threadPool.shutdown();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getCount());
    }
}
