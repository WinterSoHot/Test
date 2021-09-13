package cn.dx.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * <p>
 * level: easy
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/21
 **/
public class MinCostClimbStairs {
    public int minCostClimbingStairs(int[] cost) {
        // 动态规划
        int n = cost.length;
        // 最多到最高楼梯
        int[] dp = new int[n];
        // 前两个初始位置，没有消耗
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < n; i++) {
            // 当前是走一步和走两步的最小值，因为每步需要消耗之前所在位置的代价，因此比较一步之前的代价
            // 和两步的代价
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        // 最后顶部在楼梯之上是根据一步还是二步的结果
        return Math.min(dp[n - 1] + cost[n - 1], dp[n - 2] + cost[n - 2]);
    }

    public static void main(String[] args) {
        MinCostClimbStairs mccs = new MinCostClimbStairs();
        System.out.println(mccs.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(mccs.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
