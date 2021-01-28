package cn.dx.leetcode;

/**
 * 724. 寻找数组的中心索引
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * <p>
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * <p>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * level: easy
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/28
 */
public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int left = 0;
        int right = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            right += nums[i];
        }
        for (int pivot = 0; pivot < n; pivot++) {
            if (left == right) {
                return pivot;
            }
            left += nums[pivot];
            if (pivot + 1 == n) {
                right = 0;
            } else {
                right -= nums[pivot + 1];
            }
        }
        return -1;
    }
}
