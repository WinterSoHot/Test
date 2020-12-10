package cn.dx.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/4
 **/
public class ClimbStairs {
    public int climbStairs(int n) {
        // 状态：楼梯变化 dp: dp[n]走到n有几种方法
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // 选择
            for (int j = 1; j <= 2; j++) {
                // 表示能走当前j台阶
                if (i - j >= 0) {
                    // 加上上次的方法
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        ClimbStairs cs = new ClimbStairs();
        System.out.println(cs.climbStairs(3));
        System.out.println(cs.climbStairs(2));
        System.out.println(cs.climbStairs(4));
    }
}
