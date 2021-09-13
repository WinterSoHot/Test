package cn.dx.thread.synchronize;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawTest
 * @description 模拟多线程取钱
 * @date 20-5-16 下午9:18
 * @see DrawThread2
 **/
public class DrawTest3 {
    public static void main(String[] args) {
        // 在线程中使用同步代码块
        Account acct = new Account("1231321",1000);
        new DrawThread3("张三",acct,800).start();
        new DrawThread3("李四",acct,800).start();
    }
}
