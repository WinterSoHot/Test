package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 绝对差不超过限制的最长连续子数组
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * <p>
 * 如果不存在满足条件的子数组，则返回 0 。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xq91vm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/1
 */
public class LongestSubArray {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right;
        int ans = 0;
        // TreeMap 保存了键值对中键的大小
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (right = 0; right < nums.length; right++) {
            m.put(nums[right], m.getOrDefault(nums[right], 0) + 1);
            // 最大的键和最小的键大小超过 limit，删除元素，并且left后移
            while (m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {
                m.put(nums[left], m.get(nums[left]) - 1);
                if (m.get(nums[left]) == 0) {
                    m.remove(nums[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int longestSubarray2(int[] nums, int limit) {
        // 双指针
        int left = 0, right;
        int ans = 0;
        // 计算当前区间的最大最小值
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();
        for (right = 0; right < nums.length; right++) {
            while (!maxd.isEmpty() && maxd.peekLast() < nums[right]) {
                maxd.pollLast();
            }
            while (!mind.isEmpty() && mind.peekLast() > nums[right]) {
                mind.pollLast();
            }
            maxd.addLast(nums[right]);
            mind.addLast(nums[right]);
            while (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == nums[left]) {
                    maxd.poll();
                }
                if (mind.peek() == nums[left]) {
                    mind.poll();
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
