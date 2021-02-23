package cn.dx.leetcode;

/**
 * 1052. 爱生气的书店老板
 * <p>
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/23
 */
public class GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = grumpy.length;
        int totoal = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                totoal += customers[i];
            }
        }
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += grumpy[i] * customers[i];
        }
        int maxIncrease = increase;
        for (int i = X; i < n; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(increase, maxIncrease);
        }
        return totoal + maxIncrease;
    }

    public static void main(String[] args) {
        GrumpyBookstoreOwner gbo = new GrumpyBookstoreOwner();
        System.out.println(gbo.maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
    }
}
