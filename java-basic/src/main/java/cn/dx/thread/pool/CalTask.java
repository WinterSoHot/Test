package cn.dx.thread.pool;

import java.util.concurrent.RecursiveTask;

/**
 * CalTask 继承RecursiveTask<T>实现返回值
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午9:07
 **/
public class CalTask extends RecursiveTask<Integer> {
    private final static int THRESHOLD = 20;
    private int start;
    private int end;
    private int[] arr;

    public CalTask(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int middle = end + (start - end) / 2;
            CalTask left = new CalTask(start, middle, arr);
            CalTask right = new CalTask(middle, end, arr);
            left.fork();
            right.fork();
            // 将两个小任务的结果合并起来
            return left.join() + right.join();
        }
    }
}
