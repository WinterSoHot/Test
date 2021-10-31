package cn.dx.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 260. 只出现一次的数字 III
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 */
public class SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        int[] ans = new int[2];
        int index = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        for (Integer item : set) {
            ans[index++] = item;
        }
        return ans;
    }

    public int[] singleNumber2(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & -xorsum);
        int ans1 = 0, ans2 = 0;
        for (int num : nums) {
            if ((num & lsb) == 0) {
                ans1 ^= num;
            } else {
                ans2 ^= num;
            }
        }
        return new int[]{ans1, ans2};
    }
}
