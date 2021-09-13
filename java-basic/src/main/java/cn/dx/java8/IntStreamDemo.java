package cn.dx.java8;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/25
 */
@Slf4j
public class IntStreamDemo {
    public static void main(String[] args) {
        // 勾股数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.limit(5)
                .forEach(
                        t -> log.info("a = {}, b = {}, c= {} ", t[0], t[1], t[2])
                );
    }
}
