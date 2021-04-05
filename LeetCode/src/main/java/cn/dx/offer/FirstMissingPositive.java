package cn.dx.offer;

/**
 * 41. 缺失的第一个正数
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/4
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {

        int len = nums.length;


        for (int i = 0; i < len; i++) {
            if (nums[i] < 0) {
                // 将负数剔除
                nums[i] = len + 1;
            }
        }
        for (int num : nums) {
            // 当前位置出现的数
            num = Math.abs(num);
            if (num < len) {
                // 当前num出现了，就把出现的数的位置再数变成负数
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                // 找到第一个位置不为负数
                return i + 1;
            }
        }
        return len + 1;
    }
}
