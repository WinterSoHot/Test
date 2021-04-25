package cn.dx.thread.comunication;

/**
 * @author dongxian
 * @version 1.0
 * @className ExHandler
 * @description TODO
 * @date 20-5-17 下午6:46
 **/
public class ExHandler {
    static class  MyExHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + "线程出现了异常 "+e);
        }
    }
    public static void main(String[] args) {
        //捕获线程组中线程的异常
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
        int a = 5/0;
        // 虽然捕获了异常，但是和catch不一样，程序仍然不是正常结束，和catch不一样
        // catch 不会向上层传播异常
        // 但异常处理器会传播异常给上层，导致程序异常结束
        System.out.println("程序正常结束");
    }
}
