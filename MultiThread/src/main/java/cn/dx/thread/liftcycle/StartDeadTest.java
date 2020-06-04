package cn.dx.thread.liftcycle;

/**
 * @author dongxian
 * @version 1.0
 * @className StartDeadTest
 * @description 调用已经死亡的线程
 * @date 20-5-16 下午3:11
 **/
public class StartDeadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            private int i;
            @Override
            public void run() {
                for (;i<100;i++){
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+  " " +i);
            if (i == 20){
                t.start();
                t.join();
                System.out.println("线程启动后的状态："+t.isAlive());
            }
            if (i>20 && !t.isAlive()){
                t.start();
            }
        }
    }
}
