package cn.dx.thread.synchronize;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongxian
 * @version 1.0
 * @className Account
 * @description 使用同步锁Lock进行线程安全访问
 * @date 20-5-16 下午9:12
 **/
public class Account2 {

    //定义锁对象 可重入锁：一个线程可以对已经加锁的ReentrantLock锁再次加锁，ReentrantLock对象会维持一个计数器来最终lock()的嵌套调用
    // 必须显示调用unlock()解锁，所以一段被锁的代码可以调用另一个被相同锁保护的方法。
    private final ReentrantLock lock = new ReentrantLock();
    private String accountNo;
    private double balance;

    public Account2(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Account2() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Account2 setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Account2 setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account2 account = (Account2) o;
        return accountNo.equals(account.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    // 提供一个线程安全的draw()来完成取钱操作
    public  void draw(double drawAmount){
        // 加锁
        lock.lock();
        try {
            if (balance > drawAmount){
                System.out.println(Thread.currentThread().getName() + "取钱成功，吐出钞票："+drawAmount);
                balance -= drawAmount;
                System.out.print("\t余额为："+balance);
            }else {
                System.out.println(Thread.currentThread().getName()+"取钱失败，余额不足！");
            }
        }finally {
            // 建议在finally中确保在必要时释放锁
            lock.unlock();
        }

    }
}
