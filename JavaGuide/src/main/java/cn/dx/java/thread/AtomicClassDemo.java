package cn.dx.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class AtomicClassDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.getAndIncrement());
    }
}
