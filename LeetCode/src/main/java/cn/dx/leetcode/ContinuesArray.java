package cn.dx.leetcode;

/**
 * 525. 连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/6/3
 */
public class ContinuesArray {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int match = 0;
        int res = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i] == 0 ? -1 : 1;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + (nums[i] == 0 ? -1 : 1);
                if (dp[i][j] == 0) {
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
