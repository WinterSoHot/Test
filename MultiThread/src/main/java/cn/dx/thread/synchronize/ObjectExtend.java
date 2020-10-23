package cn.dx.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/22
 */
public class ObjectExtend {
    synchronized void m1() {
        System.out.println("m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end...");
    }

    public static void main(String[] args) {
        new TT().m1();
    }
}

class TT extends ObjectExtend {
    @Override
    synchronized void m1() {
        System.out.println("child m1 start...");
        super.m1();
        System.out.println("child m1 end...");
    }
}
