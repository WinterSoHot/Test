package cn.dx.thread.comunication;

import java.util.concurrent.BlockingQueue;

/**
 * @author dongxian
 * @version 1.0
 * @className Producer
 * @description TODO
 * @date 20-5-17 下午6:28
 **/
public class Producer extends Thread{
    private BlockingQueue<String> bq;

    public Producer(String name, BlockingQueue<String> bq) {
        super(name);
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{
                "Java",
                "CPP",
                "Python"
        };

        for (int i = 0; i < 999999999; i++) {
            System.out.println(getName()+"生产者准备生产集合元素");
            try {
                Thread.sleep(1000);
                // 准备放入元素，如果集合已满，则线程阻塞
                bq.put(strArr[i%3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成："+bq);
        }
    }
}
