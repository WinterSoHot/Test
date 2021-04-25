package cn.dx.thread.control;

/**
 * @author dongxian
 * @version 1.0
 * @className JoinThread
 * @description TODO
 * @date 20-5-16 下午3:16
 **/
public class JoinThread extends Thread{

    public JoinThread(String name){
        super(name);
    }
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new JoinThread("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i==20){
                JoinThread jt = new JoinThread("被Join的线程");
                jt.start();
                // main线程调用了jt的join()方法，必须等jt线程执行结束才回向下执行
                // join()还有另外两种重载方法
                jt.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
