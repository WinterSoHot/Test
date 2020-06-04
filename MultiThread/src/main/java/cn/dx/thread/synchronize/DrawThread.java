package cn.dx.thread.synchronize;

/**
 * @author dongxian
 * @version 1.0
 * @className DrawThread
 * @description 取钱线程
 * @date 20-5-16 下午9:14
 **/
public class DrawThread extends Thread{
    private Account account;
    private double drawAmount;

    public DrawThread(String name,Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        if (account.getBalance() > drawAmount){
            System.out.println(getName()+ "取钱成功，吐出钞票："+drawAmount);
            account.setBalance(account.getBalance()  - drawAmount);
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println("\t余额为："+account.getBalance());
        }else {
            System.out.println(getName()+"取钱失败，余额不足");
        }
    }
}
