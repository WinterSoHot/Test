package cn.dx.thread.create;

/**
 * @author dongxian
 * @version 1.0
 * @className SecondThreadTest
 * @description TODO
 * @date 20-5-16 上午11:47
 **/
public class SecondThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                SecondThread st = new SecondThread();
                // 两个线程共享 Runnable实现的对象，因此st的i变量是两个线程公有的
                new Thread(st,"Thread1").start();
                new Thread(st,"Thread2").start();
            }
        }
    }
}
