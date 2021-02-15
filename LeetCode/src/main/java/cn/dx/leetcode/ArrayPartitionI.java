package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 561. 数组拆分 I
 * <p>
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * <p>
 * 返回该 最大总和 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/16
 */
public class ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        int ret = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            ret += nums[i];
        }
        return ret;
    }
}
