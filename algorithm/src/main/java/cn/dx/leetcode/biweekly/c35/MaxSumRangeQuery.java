package cn.dx.leetcode.biweekly.c35;

import java.util.*;

/**
 * 有一个整数数组 nums ，和一个查询数组 requests ，其中 requests[i] = [starti, endi] 。第 i 个查询求 nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi] 的结果 ，starti 和 endi 数组索引都是 从 0 开始 的。
 * <p>
 * 你可以任意排列 nums 中的数字，请你返回所有查询结果之和的最大值。
 * <p>
 * 由于答案可能会很大，请你将它对 109 + 7 取余 后返回。
 * <p>
 * https://leetcode-cn.com/contest/biweekly-contest-35/problems/maximum-sum-obtained-of-any-permutation/
 * <p>
 * 难度：中等
 * <p>
 * 74 / 82 个通过测试用例 超时
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/19
 */
public class MaxSumRangeQuery {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] request : requests) {
            int start = request[0];
            int end = request[1];
            for (int i = start; i < end + 1; i++) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        Arrays.sort(nums);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            res += list.get(i).getValue() * nums[nums.length - list.size() + i];
            res %= 1E9 + 7;
        }
        return res;
    }
}
