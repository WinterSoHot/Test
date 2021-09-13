package cn.dx.thread.other;

/**
 * MyThread TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午10:17
 **/
public class MyThread extends Thread {
    private Account account;

    public MyThread(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i==6){
                account.setName(getName());
            }
            System.out.println(account.getName() + " 账户的i的值"+i);
            
        }
    }
}
