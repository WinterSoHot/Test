package cn.dx.thread.synchronize;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawThread
 * @description 取钱线程，增加同步代码块
 * @date 20-5-16 下午9:14
 **/
public class DrawThread2 extends Thread{
    private Account account;
    private double drawAmount;

    public DrawThread2(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        // 将account作为同步监视器，任何线程进入下面的同步代码块
        // 必须先获得对account的锁定——其他线程无法获得，也就无法修改它
        // 这种做法符合 "加锁->修改->释放锁"的逻辑
        synchronized (this.account) {
            if (account.getBalance() > drawAmount) {
                System.out.println(getName() + "取钱成功，吐出钞票：" + drawAmount);
                account.setBalance(account.getBalance() - drawAmount);
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
                System.out.println("\t余额为：" + account.getBalance());
            } else {
                System.out.println(getName() + "取钱失败，余额不足");
            }
        }
    }
}
