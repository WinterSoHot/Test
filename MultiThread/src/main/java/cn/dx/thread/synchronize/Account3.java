package cn.dx.thread.synchronize;

import java.util.Objects;

/**
 * @author dongxian
 * @version 1.0
 * @className Account
 * @description 使用同步监视器线程通信
 * @date 20-5-16 下午9:12
 **/
public class Account3 {
    private String accountNo;
    private double balance;

    public Account3(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Account3() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Account3 setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Account3 setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account3 account = (Account3) o;
        return accountNo.equals(account.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    // 提供一个线程安全的draw()来完成取钱操作
    public synchronized void draw(double drawAmount)  {
        if (!flag){
            // 表示当前没有存款，暂停当前线程
            try {
                wait();
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
            this.notifyAll();
        }else {
            System.out.println(Thread.currentThread().getName()+"取钱失败，余额不足！");
        }
    }

    private boolean flag = true;
    // 存款
    public synchronized void deposit(double depositAmount) {
        if (flag){
            // 当前状态已有存款，则暂停当前线程
            try {
                wait();
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
        notifyAll();
    }
}
