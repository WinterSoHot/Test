package cn.dx.collection;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * IntStreamTest TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-19 下午10:15
 **/
public class IntStreamTest {
    public static void main(String[] args) {
        IntStream is = IntStream.builder()
                .add(1)
                .add(2)
                .add(3)
                .add(4)
                .add(5).build();
        System.out.println(is.max().getAsInt());
        System.out.println(is.min().getAsInt());
        System.out.println(is.count());
        System.out.println(is.sum());
        System.out.println(is.average());


    }

}
