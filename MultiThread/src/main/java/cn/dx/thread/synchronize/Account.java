package cn.dx.thread.synchronize;

import java.util.Objects;

/**
 * @author dongxian
 * @version 1.0
 * @className Account
 * @description TODO
 * @date 20-5-16 下午9:12
 **/
public class Account {
    private String accountNo;
    private double balance;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Account() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Account setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Account setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    // 提供一个线程安全的draw()来完成取钱操作
    public synchronized void draw(double drawAmount) {
        if (balance > drawAmount) {
            System.out.println(Thread.currentThread().getName() + "取钱成功，吐出钞票：" + drawAmount);
            balance -= drawAmount;
            System.out.println("\t余额为：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败，余额不足！");
        }
    }
}
