package cn.dx.leetcode;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * level: easy
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/4
 **/
public class MaxAverageSubArray {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int ans;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        ans = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            ans = Math.max(ans, sum);
        }
        return 1.0 * ans / k;
    }
}
