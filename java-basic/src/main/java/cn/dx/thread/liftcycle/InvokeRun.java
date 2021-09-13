package cn.dx.thread.liftcycle;

/**
 * @author dongxian
 * @version 1.0
 * @className InvokeRun
 * @description 直接调用run()
 * @date 20-5-16 下午2:58
 **/
public class InvokeRun extends Thread {
    private int i;
    @Override
    public void run() {
        for (;i<100;i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+  " " +i);
            if (i == 20){
                // 这只是调用对象方法，并不是启动线程
                new InvokeRun().run();
                new InvokeRun().run();
            }
        }
    }
}
