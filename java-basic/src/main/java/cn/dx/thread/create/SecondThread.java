package cn.dx.thread.create;

/**
 * @author dongxian
 * @version 1.0
 * @className SecondThread
 * @description TODO
 * @date 20-5-16 上午11:46
 **/
public class SecondThread implements Runnable {

    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
