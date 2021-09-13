package cn.dx.leetcode.biweekly.c35;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/20
 */
public class MinSubArr2 {

    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 求数组中所有元素和被p除的余数
        long r = sum % p;
        if (r == 0) {
            return 0;
        }

        // 求前缀和
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int min = Integer.MAX_VALUE;
        // 哈希表保存上个前缀和的位置，前缀和需要除以p取余数
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            Integer index = prefixSumMap.get((prefixSum[i] - r) % p);
            if (index != null) {
                min = Math.min(min, i - index);
            }
            prefixSumMap.merge(prefixSum[i] % p, i, Math::max);
        }
        return (min == Integer.MAX_VALUE || min == n) ? -1 : min;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 32, 31, 18, 34, 20, 21, 13, 1, 27, 23, 22, 11, 15, 30, 4, 2};
        int p = 148;
        MinSubArr2 msa = new MinSubArr2();
        System.out.println(msa.minSubarray(nums, p));
    }
}
