package cn.dx.thread.control;

/**
 * @author dongxian
 * @version 1.0
 * @className DaemonThreadTest
 * @description 后台线程：所有前台线程都死亡，后台线程会自动死亡，典型的后台进程 JVM的垃圾回收线程
 * @date 20-5-16 下午8:51
 **/
public class DaemonThread extends Thread{
    private int i;
    @Override
    public void run() {
        for (; i < 1000; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        DaemonThread dt = new DaemonThread();
        dt.setName("后台线程");
        dt.setDaemon(true); // 在start()之前执行
        dt.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        // 前台main线程结束
        // 后台线程也随之结束
    }
}
