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
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.put("Java");
        bq.put("Java");
        bq.put("Java"); // 阻塞线程
    }
}
