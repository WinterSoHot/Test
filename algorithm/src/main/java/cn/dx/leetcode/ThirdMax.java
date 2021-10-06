package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 414. 第三大的数
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * @author gudongxian
 * @date 2021/10/6
 */
public class ThirdMax {
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = n - 2, diff = 1; i >= 0; i--) {
            if (nums[i] != nums[i + 1] && ++diff == 3) {
                return nums[i];
            }
        }
        return nums[n - 1];
    }

    public static void main(String[] args) {
        ThirdMax thirdMax = new ThirdMax();
        System.out.println(thirdMax.thirdMax(new int[]{3, 2, 1}));
        System.out.println(thirdMax.thirdMax(new int[]{1, 2}));
        System.out.println(thirdMax.thirdMax(new int[]{2, 2, 3, 1}));
    }

}
