package cn.dx.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dongxian
 * @version 1.0
 * @className ThirdThreadTest
 * @description 使用Callable和Future 创建线程
 * @date 20-5-16 下午2:40
 **/
public class ThirdThreadTest {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>) () -> {
            int i = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            }
            return i;
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                new Thread(task,"有返回值的线程").start();
            }
        }
        try {
            // task.get() 会导致线程阻塞，直到Callable实现类的call()执行结束，也可以通过重载的方法指定阻塞的时间
            System.out.println("子线程的返回值"+task.get()+" 是否完成："+task.isDone()+" 是否取消："+task.isCancelled());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
