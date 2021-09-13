package cn.dx.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/23
 */
public class SetDemo {
    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>();
        LinkedHashSet<Integer> set2 = new LinkedHashSet<>();
        set1.add(2);
        set1.add(1);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        set2.add(3);
        set2.add(2);
        set2.add(1);
        set2.add(4);
        set2.add(5);
        System.out.println(set1);
        System.out.println(set2);
    }
}
