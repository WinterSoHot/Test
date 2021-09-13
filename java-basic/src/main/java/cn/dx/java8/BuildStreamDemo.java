package cn.dx.java8;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构建流
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/26
 */
@Slf4j
public class BuildStreamDemo {
    public static void main(String[] args) {
        // 从值构建流
        Stream<String> stream = Stream.of("Java8", "Lambdas");
        stream.map(String::toUpperCase).forEach(log::info);

        Stream<String> empty = Stream.empty();
        empty.forEach(log::info);

        // 从数组构建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        log.info("sum = {} ", sum);

        // 从文件生成流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("tr"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("uniqueWords = {}", uniqueWords);

        // 由函数生成流：无限流
        log.info("函数生成流，无限流");
        Stream.iterate(0, n -> n + 2)
                .skip(5)
                .limit(5)
                .forEach(i -> log.info("连加： {}", i));
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(i -> log.info("斐波拉契数: {}", i));

        Stream.generate(Math::random)
                .limit(5)
                .forEach(i -> log.info("generate value = {}", i));

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib)
                .limit(10)
                .forEach(i -> log.info("fib generate = {}", i));

    }
}
