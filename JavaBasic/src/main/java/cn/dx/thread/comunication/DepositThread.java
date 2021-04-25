package cn.dx.thread.comunication;


import cn.dx.thread.synchronize.Account3;

/**
 * @author dongxian
 * @version 1.0
 * @className DepositThread
 * @description TODO
 * @date 20-5-17 下午5:57
 **/
public class DepositThread extends Thread{
    private Account3 account;
    private double depositAmount;

    public DepositThread(String name,Account3 account, double depositAmount) {
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposit(depositAmount);
        }
    }
}
