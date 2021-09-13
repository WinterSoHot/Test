package cn.dx.thread;

/**
 * 双重校验锁实现对象单例（线程安全）
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/25
 */
public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
        // 不暴露构造方法
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                // 对类加锁
                if (instance == null) {
                    // 检查是否被其他线程创建
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
