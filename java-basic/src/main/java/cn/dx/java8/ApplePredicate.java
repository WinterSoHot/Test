package cn.dx.java8;

import cn.dx.java8.entity.Apple;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/27
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
