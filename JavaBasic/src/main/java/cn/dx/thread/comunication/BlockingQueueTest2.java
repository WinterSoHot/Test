package cn.dx.thread.comunication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author dongxian
 * @version 1.0
 * @className BlockingQueueTest
 * @description TODO
 * @date 20-5-17 下午6:26
 **/
public class BlockingQueueTest2 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
        Producer producer = new Producer("Producer1",bq);
        Producer producer1 = new Producer("Producer2",bq);
        Producer producer2= new Producer("Producer3",bq);
        Consumer consumer = new Consumer("Consumer",bq);
        producer.start();
        producer1.start();
        producer2.start();
        consumer.start();
    }

}


