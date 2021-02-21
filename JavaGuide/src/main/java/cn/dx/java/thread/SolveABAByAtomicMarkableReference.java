package cn.dx.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class SolveABAByAtomicMarkableReference {
    private static AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        //ABA问题， instance2 获取到标记状态，然后交给 instance1 修改又变回来。此时 instance2 操作仍旧成功
        Runnable instance1 = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(100, 101,
                    atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
            atomicMarkableReference.compareAndSet(101, 100,
                    atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
        };
        Runnable instance2 = () -> {
            boolean marked = atomicMarkableReference.isMarked();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean c3 = atomicMarkableReference.compareAndSet(100, 101, marked, !marked);
            // 实际 true ，应该是 false
            System.out.println(c3);
        };
        ExecutorService threadPool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                r -> new Thread(r, "atomic-main"));
        threadPool.execute(instance1);
        threadPool.execute(instance2);
        threadPool.shutdown();
    }
}
