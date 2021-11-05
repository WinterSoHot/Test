package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1218. 最长定差子序列
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * <p>
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 *
 * @author gudongxian
 * @date 2021/11/5
 */
public class LongestArithmeticSubsequenceOfGivenDifference {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        int[] dp = new int[n];
        Map<Integer, Integer> visited4Index = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            if (visited4Index.containsKey(arr[i] - difference)) {
                dp[i] = dp[visited4Index.get(arr[i] - difference)] + 1;
            }
            visited4Index.put(arr[i], i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestArithmeticSubsequenceOfGivenDifference main = new LongestArithmeticSubsequenceOfGivenDifference();
        int ans = main.longestSubsequence(new int[]{1, 2, 3, 4}, 1);
        System.out.println("ans = " + ans);
        ans = main.longestSubsequence(new int[]{1, 3, 5, 7}, 1);
        System.out.println("ans = " + ans);
        ans = main.longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2);
        System.out.println("ans = " + ans);
        ans = main.longestSubsequence(new int[]{3, 4, -3, -2, -4}, -5);
        System.out.println("ans = " + ans);
    }
}
