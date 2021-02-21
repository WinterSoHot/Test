package cn.dx.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class AtomicIntegerDefectDemo {
    public static void main(String[] args) {
        defectOfABA();
    }

    private static void defectOfABA() {
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        Runnable target = () -> {
            final int currentValue = atomicInteger.get();
            System.out.println(Thread.currentThread().getName() + "---- currentValue = " + currentValue);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean casResult = atomicInteger.compareAndSet(1, 2);
            System.out.println(Thread.currentThread().getName()
                    + " ------ currentValue=" + currentValue
                    + ", finalValue=" + atomicInteger.get()
                    + ", compareAndSet Result=" + casResult);
        };
        Thread coreThread = new Thread(target);
        coreThread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread amateurThread = new Thread(
                () -> {
                    int currentValue = atomicInteger.get();
                    boolean casResult = atomicInteger.compareAndSet(1, 2);
                    System.out.println(Thread.currentThread().getName()
                            + " ------ currentValue=" + currentValue
                            + ", finalValue=" + atomicInteger.get()
                            + ", compareAndSet Result=" + casResult);

                    currentValue = atomicInteger.get();
                    casResult = atomicInteger.compareAndSet(2, 1);
                    System.out.println(Thread.currentThread().getName()
                            + " ------ currentValue=" + currentValue
                            + ", finalValue=" + atomicInteger.get()
                            + ", compareAndSet Result=" + casResult);
                }
        );
        amateurThread.start();
    }
}
