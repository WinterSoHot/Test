package cn.dx.thread.practice;

/**
 * NumberThread TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午10:32
 **/
public class NumberThread extends Thread {
    private final Test1 obj;

    public NumberThread(String name, Test1 obj) {
        super(name);
        this.obj = obj;
    }

    @Override
    public void run() {
//        synchronized (obj) {
//            for (int i = 1; i <= 52; i++) {
//                if (!obj.flag) {
//                    try {
//                        obj.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.print(i);
//                if (i % 2 == 0) {
//                    obj.flag = false;
//                    obj.notifyAll();
//                }
//            }
//        }

        obj.lock.lock();
        try {
            for (int i = 1; i <= 52; i++) {
                if (!obj.flag) {
                    try {
                        obj.cond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(i);
                if (i % 2 == 0) {
                    obj.flag = false;
                    obj.cond.signal();
                }
            }
        }finally {
            obj.lock.unlock();
        }


    }
}
