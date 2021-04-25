package cn.dx.thread.other;

/**
 * Account TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 上午10:15
 **/
public class Account {
    private ThreadLocal<String> name = new ThreadLocal<>();

    public Account(String name) {
        this.name.set(name);
        // 访问当前线程name副本的值
        System.out.println("----" + this.name.get());
    }

    public String getName() {
        return this.name.get();
    }

    public Account setName(String name) {
        this.name.set(name);
        return this;
    }
}
