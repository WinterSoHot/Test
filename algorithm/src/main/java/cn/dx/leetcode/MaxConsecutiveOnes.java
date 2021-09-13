package cn.dx.leetcode;

/**
 * 485. 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/15
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0;
        int cur = 0;
        for (int num : nums) {
            if (num == 1) {
                cur++;
            } else {
                ret = Math.max(cur, ret);
                cur = 0;
            }
        }
        return Math.max(cur, ret);
    }
}
