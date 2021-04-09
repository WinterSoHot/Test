package cn.dx.java.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/27
 */
public class ThreadLocalDemo implements Runnable {
    ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(demo, "" + i);
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "default formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());

    }
}
