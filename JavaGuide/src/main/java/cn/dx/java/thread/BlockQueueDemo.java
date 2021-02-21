package cn.dx.java.thread;

import java.util.concurrent.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class BlockQueueDemo {


    public static void main(String[] args) {
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
        Runnable instanceGet = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + blockingDeque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable instancePut = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingDeque.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService threadPool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "blockQueue-main");
                    }
                });
        threadPool.execute(instanceGet);
        threadPool.execute(instancePut);
        threadPool.shutdown();
    }
}
