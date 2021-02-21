package cn.dx.java.thread;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class SkipListDemo {
    public static void main(String[] args) {
        Set<Integer> ss = new ConcurrentSkipListSet<>();
        ss.add(1);
        ss.add(2);
        ss.add(3);
        System.out.println(ss);
    }
}
