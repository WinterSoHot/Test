package cn.dx.java8;

import java.util.stream.Stream;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/27
 */
public class StreamOf {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("A", "B", "C", "D");
        System.out.println("第一次遍历");
        stream.forEach(System.out::println);
        System.out.println("第二次遍历");
        stream.forEach(System.out::println);
    }
}
