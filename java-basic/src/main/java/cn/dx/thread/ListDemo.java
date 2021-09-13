package cn.dx.thread;

import java.util.concurrent.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class ListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> coal = new CopyOnWriteArrayList<>();
        coal.add(1);
        Runnable instanceWrite = () -> {
            for (int i = 0; i < 100; i++) {
                coal.add(i);
            }
        };
        Runnable instanceRead = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(coal.get(coal.size() - 1));
            }
        };
        ExecutorService threadPool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                r -> new Thread(r, "Copy-main"));
        threadPool.execute(instanceWrite);
        threadPool.execute(instanceRead);
        threadPool.shutdown();

    }
}
