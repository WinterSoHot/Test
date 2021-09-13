package cn.dx.leetcode.weekly.c229;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class MaximumScoreFromPerformingMultiplicationOperations {
    int res = Integer.MIN_VALUE;
    int[][] table;

    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int n = nums.length;
        int[][][] dp = new int[m + 1][n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][1][n] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int s = 1; s <= n; s++) {
                for (int e = n; e > 0; e--) {
                    if (n - (e - s) == i) {

                    }
                }
            }
        }
        table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = Integer.MIN_VALUE;
            }
        }
        maximumScore(nums, multipliers, 0, 0, nums.length - 1, 0);
        return res;
    }

    private void maximumScore(int[] nums, int[] multipliers, int i, int start, int end, int score) {
        if (i == multipliers.length) {
            res = Math.max(res, score);
            return;
        }
        if (score < table[start][end]) {
            return;
        }
        table[start][end] = score;
        maximumScore(nums, multipliers, i + 1, start + 1, end, score + multipliers[i] * nums[start]);
        maximumScore(nums, multipliers, i + 1, start, end - 1, score + multipliers[i] * nums[end]);
    }
}
