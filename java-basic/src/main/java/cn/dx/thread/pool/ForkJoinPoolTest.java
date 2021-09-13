package cn.dx.thread.pool;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author dongxian
 * @version 1.0
 * @className ForkJoinPoolTest
 * @description Java7 Java8 提供的将一个任务分成多个任务，再将多个任务的计算结果合并
 * @date 20-5-18 上午8:30
 **/
public class ForkJoinPoolTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        forkJoinPool.submit(new PrintTask(0, 300));
//        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
//        forkJoinPool.shutdown();

        int[] arr = new int[100];
        Random random = new Random();
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            int tmp = random.nextInt(20);
            total += (arr[i] = tmp);

        }
        System.out.println(total);
        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> res = forkJoinPool1.submit(new CalTask(0, arr.length, arr));
        System.out.println(res.get());
        forkJoinPool1.shutdown();
    }
}
