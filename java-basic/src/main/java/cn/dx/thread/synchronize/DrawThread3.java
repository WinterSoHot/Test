package cn.dx.thread.synchronize;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawThread
 * @description 取钱线程，增加同步方法
 * @date 20-5-16 下午9:14
 **/
public class DrawThread3 extends Thread{
    private Account account;
    private double drawAmount;

    public DrawThread3(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        // 同步方法的同步监视器为this，this代表调用draw()的对象
        // 即线程进入draw()方法之前，必须对account对象加锁
        account.draw(drawAmount);
    }
}
