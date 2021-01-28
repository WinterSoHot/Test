package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/20
 **/
public class MaxProductOfThreeNum {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int v = nums[n - 3] * nums[n - 2] * nums[n - 1];
        v = Math.max(v, nums[0] * nums[1] * nums[n - 1]);
        return v;
    }
}
