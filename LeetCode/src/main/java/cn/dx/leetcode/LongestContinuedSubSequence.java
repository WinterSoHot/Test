package cn.dx.leetcode;

/**
 * level:easy
 * <p>
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * <p>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，
 * 如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/24
 **/
public class LongestContinuedSubSequence {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int pre = nums[0];
        int cur = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int v = nums[i];
            if (v > pre) {
                cur++;
            } else {
                ans = Math.max(ans, cur);
                cur = 1;
            }
            pre = v;
        }
        ans = Math.max(ans, cur);
        return ans;
    }
}
