package cn.dx.thread.synchronize;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongxian
 * @version 1.0
 * @className Account
 * @description 使用同步锁Lock进行线程安全访问，Condition线程通信
 * @date 20-5-16 下午9:12
 **/
public class Account4 {

    //定义锁对象 可重入锁：一个线程可以对已经加锁的ReentrantLock锁再次加锁，ReentrantLock对象会维持一个计数器来最终lock()的嵌套调用
    // 必须显示调用unlock()解锁，所以一段被锁的代码可以调用另一个被相同锁保护的方法。
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();
    private String accountNo;
    private double balance;

    public Account4(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Account4() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Account4 setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Account4 setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account4 account = (Account4) o;
        return accountNo.equals(account.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    private boolean flag;
    public  void draw(double drawAmount)  {

        lock.lock();
        try {
            if (!flag){
                // 表示当前没有存款，暂停当前线程
                try {
                    cond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (balance > drawAmount){
                System.out.println(Thread.currentThread().getName() + "取钱成功，吐出钞票："+drawAmount);
                balance -= drawAmount;
                System.out.println("\t余额为："+balance);
                // 取款成功，修改标识位
                flag = false;
                // 通知其他线程
                cond.signalAll();
            }else {
                System.out.println(Thread.currentThread().getName()+"取钱失败，余额不足！");
            }
        }finally {
            lock.unlock();
        }

    }

    // 存款
    public  void deposit(double depositAmount) {

        lock.lock();
        try {
            if (flag){
                // 当前状态已有存款，则暂停当前线程
                try {
                    cond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+ " 存款："+depositAmount);
            balance+= depositAmount;
            System.out.println("账户余额为："+balance);
            // 表示账户已有存款，标志位修改为true
            flag = true;
            // 唤醒其他线程
            cond.signalAll();
        }finally {
            lock.unlock();
        }

    }
}
