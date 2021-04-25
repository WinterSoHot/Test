package cn.dx.thread.control;

/**
 * @author dongxian
 * @version 1.0
 * @className YieldTest
 * @description 线程让步，不是阻塞线程，而是将线程重新转为就绪，等待再次调度
 * @date 20-5-16 下午8:59
 **/
public class YieldTest extends Thread{

    public YieldTest(String name) {
        super(name);
    }
    private int i;
    @Override
    public void run() {
        for (; i < 50; i++) {
            System.out.println(getName() + " " + i);
            if (i == 20){
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldTest yt1 = new YieldTest("高级");
        yt1.setPriority(Thread.MAX_PRIORITY);
        yt1.start();
        YieldTest yt2 = new YieldTest("低级");
        yt2.setPriority(Thread.MIN_PRIORITY);
        yt2.start();


    }
}
