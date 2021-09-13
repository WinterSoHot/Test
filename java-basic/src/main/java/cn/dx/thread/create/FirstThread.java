package cn.dx.thread.create;

/**
 * @author dongxian
 * @version 1.0
 * @className FirstThread
 * @description 通过继承Thread实现线程，重写run()，run()中的代码就是任务
 * @date 20-5-16 上午11:40
 **/
public class FirstThread extends Thread {
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }
}
