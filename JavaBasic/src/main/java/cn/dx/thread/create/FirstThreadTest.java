package cn.dx.thread.create;

/**
 * @author dongxian
 * @version 1.0
 * @className FirstThreadTest
 * @description TODO
 * @date 20-5-16 上午11:40
 **/
public class FirstThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                // 启动第一个线程
                new FirstThread().start();
                // 启动第二个线程
                new FirstThread().start();
            }

        }
    }
}
