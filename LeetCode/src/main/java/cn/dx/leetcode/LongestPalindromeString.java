package cn.dx.leetcode;

/**
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class LongestPalindromeString {
    public String longestPalindrome(String input) {
        String ans = "";
        int n = input.length();
        // 表示从 i 到 j 是否为 回文串
        boolean[][] dp = new boolean[n][n];
        // 长度
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                // 长度为1
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    // 长度为2
                    dp[i][j] = (input.charAt(i) == input.charAt(j));
                } else {
                    // 其他的
                    // 使用转移方程
                    // 前面子串是否为回文串，和当前前后位置
                    dp[i][j] = dp[i + 1][j - 1] && (input.charAt(i) == input.charAt(j));
                }
                // 修改答案
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = input.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
