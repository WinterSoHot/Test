package cn.dx.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author dongxian
 * @version 1.0
 * @className FileLockTest
 * @description 文件锁的使用
 * @date 20-5-15 下午1:16
 **/
public class FileLockTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileChannel channel = new FileOutputStream("test").getChannel();
        // 以非阻塞的方式获取锁
        FileLock lock = channel.tryLock();// 默认是排他锁
        System.out.println(lock.isShared() ? "Lock是共享锁" : "Lock是排他锁");
        //程序暂停十秒，10s内其他程序无法修改文件
        Thread.sleep(10000);
        //释放锁
        System.out.println("释放锁");
        lock.release();
    }
}
