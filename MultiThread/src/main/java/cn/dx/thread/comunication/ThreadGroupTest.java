package cn.dx.thread.comunication;

import java.util.concurrent.BlockingQueue;

/**
 * @author dongxian
 * @version 1.0
 * @className ThreadGroupTest
 * @description TODO
 * @date 20-5-17 下午6:39
 **/
public class ThreadGroupTest {
    static class MyThread extends Thread{
        public MyThread(String name) {
            super(name);
        }

        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println(getName()+"线程i的变量："+i);
            }
        }
    }
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字："+mainGroup.getName());
        System.out.println("主线程组是否为后台线程组："+mainGroup.isDaemon());

        new MyThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新的线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否为后台线程组："+tg.isDaemon());
        MyThread tt = new MyThread(tg,"tg组中的张三");
        tt.start();
        new MyThread(tg,"tg组中的李四").start();

    }
}
