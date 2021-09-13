package cn.dx.leetcode;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/21
 */
public class UnCrossedLines {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // 最长公共子序列
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
