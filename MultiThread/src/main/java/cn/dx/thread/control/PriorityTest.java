package cn.dx.thread.control;

/**
 * @author dongxian
 * @version 1.0
 * @className PriorityTest
 * @description TODO
 * @date 20-5-16 下午9:07
 **/
public class PriorityTest extends Thread{
    private int i;

    public PriorityTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (; i < 50; i++) {
            System.out.println(getName() + " 优先级为：" + getPriority()+" " + i);
        }
    }

    public static void main(String[] args) {
        // 改变主线程优先级
        Thread.currentThread().setPriority(6);
        for (int i = 0; i < 30; i++) {
            if (i==10){
                PriorityTest low = new PriorityTest("低级");
                low.start();
                System.out.println("创建之初的优先级："+low.getPriority());
                low.setPriority(Thread.MIN_PRIORITY);
            }
            if (i==20){
                PriorityTest high = new PriorityTest("高级");
                high.start();
                System.out.println("创建之初的优先级："+high.getPriority());
                high.setPriority(Thread.MAX_PRIORITY);
            }

        }
    }
}
