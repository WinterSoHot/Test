package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 乘积最大子数组
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/28
 */
public class MaxProduceSubArray {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] maxF = new int[len];
        int[] minF = new int[len];
        System.arraycopy(nums, 0, maxF, 0, len);
        System.arraycopy(nums, 0, minF, 0, len);
        for (int i = 1; i < len; i++) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        return Arrays.stream(maxF).max().getAsInt();
    }
}
