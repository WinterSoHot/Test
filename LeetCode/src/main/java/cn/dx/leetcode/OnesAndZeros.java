package cn.dx.leetcode;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/6/6
 */
public class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] info = new int[len][2];
        for (int i = 0; i < strs.length; i++) {
            for (char ch : strs[i].toCharArray()) {
                info[i][ch - '0']++;
            }
        }
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            int zeros = info[i - 1][0];
            int ones = info[i - 1][1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }
}
