package cn.dx.thread.practice;

/**
 * AlphaThread TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午10:32
 **/
public class AlphaThread extends Thread {
    private Test1 obj;

    public AlphaThread(String name, Test1 obj) {
        super(name);
        this.obj = obj;
    }

    @Override
    public void run() {
//        synchronized (obj){
//            for (int i = 'A'; i <= 'Z'; i++) {
//                if (obj.flag) {
//                    try {
//                        obj.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.print((char) i);
//                obj.flag = true;
//                obj.notify();
//            }
//        }
        obj.lock.lock();
        try {
            for (int i = 'A'; i <= 'Z'; i++) {
                if (obj.flag) {
                    try {
                        obj.cond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print((char) i);
                obj.flag = true;
                obj.cond.signal();
            }
        }finally {
            obj.lock.unlock();
        }

    }
}
