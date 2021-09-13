package cn.dx.leetcode;


import java.util.Arrays;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/6/7
 */
public class TargetSum {
    private int ans = 0;

    public int findTargetSumWaysByDp(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        // target = (sum - neg) - neg => neg = (sum - target) / 2
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) return 0;
        int n = nums.length;
        int neg = diff / 2;
        // 前i个数，得到值为j的情况个数
        int[][] dp = new int[n + 1][neg + 1];
        // base case 前0个数 选择到 0 情况只有1
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 当前的数
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                // 当前num是否可选
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    public int findTargetSumWays(int[] nums, int target) {
        // 回溯
        backtrack(nums, target, 0, 0);
        return ans;
    }

    private void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (target == sum) {
                ans++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        int ans = ts.findTargetSumWays(nums, target);
        System.out.println("ans = " + ans);
    }
}
