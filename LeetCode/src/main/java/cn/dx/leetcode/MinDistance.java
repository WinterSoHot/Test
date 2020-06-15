package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 1478. 安排邮筒 https://leetcode-cn.com/problems/allocate-mailboxes/
 * 未通过
 */
public class MinDistance {
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        int[][] rec = new int[n][n]; // rec[i][j] 用一个邮箱最小的花费
        for (int[] ints : rec) {
            Arrays.fill(ints, 0);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int mid = houses[i] + houses[j] >> 1;
                for (int x = i; x <= j; x++) {
                    rec[i][j] += Math.abs(houses[x] - mid);
                }
            }
        }

        int[][] dp = new int[n][k + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0x7fffffff);
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = rec[0][i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 2; j <= Math.min(i + 1, k); j++) {
                for (int x = j - 1; x <= i; x++) {
                    dp[i][j] = Math.min(dp[i][j], dp[x - 1][j - 1] + rec[x][i]);
                }
            }
        }
        return dp[n - 1][k];
    }
}
