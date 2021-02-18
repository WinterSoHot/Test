package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/17
 */
public class SubArraySumEqualK {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        // 前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        // 使用哈希表优化前缀和 前缀和频次
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        int count = 0;
        int preSum = 0;
        preSumFreq.put(preSum, 1);
        for (int num : nums) {
            preSum += num;
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
