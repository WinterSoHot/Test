package cn.dx.leetcode.biweekly.c35;

import java.util.Arrays;

/**
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
 * <p>
 * 请你返回你需要移除的最短子数组，如果无法满足题目要求，返回 -1 。
 * <p>
 * 子数组 定义为原数组中连续的一组元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/make-sum-divisible-by-p
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 * <p>
 * 137 / 142 个通过测试用例
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/19
 */
public class MinSubArr {
    int minRes = Integer.MAX_VALUE;
    int curRes = 0;
    int sum;

    public int minSubarray(int[] nums, int p) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] % p;
            this.sum += nums[i];
            this.sum %= p;
        }

        for (int i = 0; i < nums.length; i++) {
            backtrack(nums, p, i);
        }

        if (minRes == Integer.MAX_VALUE) {
            return -1;
        }
        return minRes;
    }

    private void backtrack(int[] nums, int p, int start) {
        if (sum % p == 0 && curRes != nums.length) {
            minRes = Math.min(minRes, curRes);
            return;
        }
        if (start < nums.length) {
            curRes += 1;
            sum -= nums[start];

            backtrack(nums, p, start + 1);

            curRes -= 1;
            sum += nums[start];
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 32, 31, 18, 34, 20, 21, 13, 1, 27, 23, 22, 11, 15, 30, 4, 2};
        int p = 148;
        MinSubArr msa = new MinSubArr();
        System.out.println(msa.minSubarray(nums, p));
    }
}
