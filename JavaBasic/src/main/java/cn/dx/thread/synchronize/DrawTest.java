package cn.dx.thread.synchronize;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawTest
 * @description 模拟多线程取钱
 * @date 20-5-16 下午9:18
 **/
public class DrawTest {
    public static void main(String[] args) {
        Account acct = new Account("1231321",1000);
        new DrawThread("张三",acct,800).start();
        new DrawThread("李四",acct,800).start();
    }
}
