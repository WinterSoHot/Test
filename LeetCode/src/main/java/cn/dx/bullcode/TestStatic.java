package cn.dx.bullcode;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TestStatic TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-19 上午9:36
 **/
public class TestStatic {
    public static TestStatic t1 = new TestStatic(); // 静态块按照声明顺序，第一步，因为执行所以执行了构造块

    {
        System.out.println("blockA");
    }

    static {
        System.out.println("blockB"); // 第二步
    }

    public static void main(String[] args) {
        // 静态块 > main() > 构造块 > 构造方法
        TestStatic ts2 = new TestStatic(); // 第三步，执行构造块
    }
}
