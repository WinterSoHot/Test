package cn.dx.thread.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Test1 TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午10:31
 **/
public class Test1 {
    // true表示数字线程，false表示字母线程
    public boolean flag = true;
    public final ReentrantLock lock = new ReentrantLock();
    public final Condition cond = lock.newCondition();

    public static void main(String[] args) {
        Test1 obj = new Test1();
        NumberThread nt = new NumberThread("NumberThread", obj);
        AlphaThread at = new AlphaThread("AlphaThread", obj);
        nt.start();
        at.start();
    }
}
