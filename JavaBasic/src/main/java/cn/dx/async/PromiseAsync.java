package cn.dx.async;

import java.util.concurrent.CompletableFuture;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/29
 */
public class PromiseAsync {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply((v) -> v + ", " + "World")
                .thenAccept(System.out::println);

        String result = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> "World"),
                        (s, v) -> s + v).join();
        System.out.println(result);

        String result2 = CompletableFuture.supplyAsync(() -> "ILLEGEAL")
                .thenApply(Integer::parseInt)
                .thenApply(r -> r * 2 * Math.PI)
                .thenApply(s -> "apply >>" + s)
                .exceptionally(ex -> "Error: " + ex.getMessage()).join();
        System.out.println(result2);

        CompletableFuture.supplyAsync(() -> "ILLEGEAL")
                .thenApply(Integer::parseInt)
                .thenApply(r -> r * 2 * Math.PI)
                .thenApply(s -> "apply >>" + s)
                .handle((res, ex) -> {
                    if (res != null) {
                        return res;
                    } else {
                        return "Error handling:" + ex.getMessage();
                    }
                });
    }
}
