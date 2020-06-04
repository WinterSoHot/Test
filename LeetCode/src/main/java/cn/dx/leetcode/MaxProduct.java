package cn.dx.leetcode;

import java.util.Arrays;

/**
 * MaxProduct TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-31 上午10:41
 **/
public class MaxProduct {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return (nums[len - 1] - 1) * (nums[len - 2] - 1);
    }
}
