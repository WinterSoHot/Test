package cn.dx.thread.pool;

import java.util.concurrent.RecursiveAction;

/**
 * PrintTask 继承RecursiveAction实现可分类的任务
 *
 * @author dongxian
 * @version 1.0
 **/
public class PrintTask extends RecursiveAction {
    // 每个小任务最多打印五十个
    private final static int THRESHOLD = 50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值" + i);
            }
        } else {
            int middle = end + (start - end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            left.fork();
            right.fork();
        }
    }
}
