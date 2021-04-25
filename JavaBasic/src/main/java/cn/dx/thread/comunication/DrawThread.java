package cn.dx.thread.comunication;

import cn.dx.thread.synchronize.Account3;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawThread
 * @description TODO
 * @date 20-5-17 下午5:56
 **/
public class DrawThread extends Thread{
    private Account3 account;
    private double drawAmount;

    public DrawThread(String name,Account3 account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.draw(drawAmount);
        }
    }
}
