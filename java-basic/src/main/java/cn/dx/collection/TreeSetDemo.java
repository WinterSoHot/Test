package cn.dx.collection;

import java.util.TreeSet;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/19
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("zhangsan");
        set.add("lisi");
        set.add("wangwu");
        set.add("zhaoliu");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
