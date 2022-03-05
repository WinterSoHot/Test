package cn.dx.java.senior.core;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class GCLogAnalysis {
    private static Random random = new Random();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.SECONDS.toMillis(1);

        LongAdder counter = new LongAdder();
        int cacheSize = 2000;
        Object[] cachedObjects = new Object[cacheSize];
        System.out.println("开始执行");
        while (System.currentTimeMillis() < endTime) {
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                cachedObjects[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束，一共生成对象次数：" + counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize * 4;
        Object result = null;
        switch (type % 4) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder sb = new StringBuilder();
                String randomString = "ABACCC";
                while (sb.length() < randomSize) {
                    sb.append(randomString);
                    sb.append(max);
                    sb.append(randomSize);
                }
                result = sb.toString();
        }
        return result;
    }
}
