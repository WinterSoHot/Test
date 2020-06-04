package cn.dx.thread.comunication;

import cn.dx.thread.synchronize.Account3;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawDepositTest
 * @description 通过同步监视器的wait(),notify(),notifyAll()实现，取款和存款交替进行
 * @date 20-5-17 下午5:59
 **/
public class DrawDepositTest {
    public static void main(String[] args) {
        Account3 account = new Account3("123", 1000);
        DrawThread drawThread = new DrawThread("1. 取款", account, 10);
        DepositThread depositThread = new DepositThread("2. 存款", account, 10);
        drawThread.start();
        depositThread.start();
    }
}
