package cn.dx.java8;

import cn.dx.java8.entity.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/27
 */
public class AppleFilterByStream {

    public static List<Apple> appleFilterByPredicate(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> res = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                res.add(apple);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("苹果", "红色", 12));
        apples.add(new Apple("香蕉", "黄色", 10));
        apples.add(new Apple("橘子", "黄色", 10));

        List<Apple> res = appleFilterByPredicate(apples, (Apple a) -> "黄色".equals(a.getColor()));
        res.forEach(System.out::println);
    }
}
