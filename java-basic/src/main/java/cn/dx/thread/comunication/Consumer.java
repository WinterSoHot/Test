package cn.dx.thread.comunication;

import java.util.concurrent.BlockingQueue;

/**
 * @author dongxian
 * @version 1.0
 * @className Producer
 * @description TODO
 * @date 20-5-17 下午6:28
 **/
public class Consumer extends Thread{
    private BlockingQueue<String> bq;

    public Consumer(String name, BlockingQueue<String> bq) {
        super(name);
        this.bq = bq;
    }

    @Override
    public void run() {
        for (int i = 0; i < 999999999; i++) {
            System.out.println(getName()+"消费者准备消费集合元素");
            try {
                Thread.sleep(200);
                // 获取元素，如果元素为空，则阻塞线程
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成："+bq);
        }
    }
}
