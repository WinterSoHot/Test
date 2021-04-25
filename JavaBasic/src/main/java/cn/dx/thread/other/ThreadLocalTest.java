package cn.dx.thread.other;

/**
 * ThreadLocalTest TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午10:14
 **/
public class ThreadLocalTest {

    public static void main(String[] args) {
        Account account = new Account("初始名");

        new MyThread("线程1",account).start();
        new MyThread("线程2",account).start();

    }
}
