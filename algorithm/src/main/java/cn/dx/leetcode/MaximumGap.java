package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * <p>
 * 如果数组元素个数小于 2，则返回 0
 * <p>
 * level: hard
 *
 * @author dongxian
 * @version 0.1
 * @date 20-11-26
 **/
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        // 可使用基数排序 进行快速排序，再比较
        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxGap = Math.max(nums[i + 1] - nums[i], maxGap);
        }
        return maxGap;
    }
}
