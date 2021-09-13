package cn.dx.leetcode.weekly.c229;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class MaximizePalindromeLengthFromSubsequences {
    public int longestPalindrome(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        String word = word1 + word2;
        int len = word.length();
        // 开始位置，序列长度
        int[][] dp = new int[len + 1][len + 1];
        int res = 0;
        // base case
        for (int i = 0; i < len; i++) {
            dp[i][1] = 1;
        }
        for (int l = 2; l <= len; ++l) {
            for (int i = 0; i + l <= len; ++i) {
                if (word.charAt(i) == word.charAt(i + l - 1)) {
                    dp[i][l] = 2 + dp[i + 1][l - 2];
                    if (i < m && i + l > m) {
                        // 在两个字符串之间计算
                        res = Math.max(res, dp[i][l]);
                    }
                } else {
                    dp[i][l] = Math.max(dp[i][l - 1], dp[i + 1][l - 1]);
                }
            }
        }
        return res;
    }
}
