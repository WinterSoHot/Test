package cn.dx.java.thread;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class SyncDemo implements Runnable {
    // 共享资源
    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int c = 0; c < 10000; c++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncDemo instance = new SyncDemo();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
