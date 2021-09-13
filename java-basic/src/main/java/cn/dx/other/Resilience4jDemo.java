package cn.dx.other;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.SneakyThrows;

import java.util.function.Function;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/25
 */
public class Resilience4jDemo {
    public static void main(String[] args) {
        CircuitBreakerRegistry circuitBreakerRegistry
                = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .ringBufferSizeInClosedState(5)
                .build();


        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
        Function<Integer, Integer> decorated = CircuitBreaker
                .decorateFunction(circuitBreaker, new Function<Integer, Integer>() {
                    @SneakyThrows
                    @Override
                    public Integer apply(Integer v) {
                        Thread.sleep(200);
                        return v;
                    }
                });

        for (int i = 0; i < 100; i++) {
            try {
                Integer out = decorated.apply(i);
                System.out.println(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
