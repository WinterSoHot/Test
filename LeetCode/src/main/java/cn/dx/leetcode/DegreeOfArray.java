package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 697. 数组的度
 * <p>
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * <p>
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class DegreeOfArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> value4index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> indexes = value4index.getOrDefault(num, new ArrayList<>());
            indexes.add(i);
            value4index.put(num, indexes);
        }
        int degress = 0;
        int ret = Integer.MAX_VALUE;
        for (List<Integer> value : value4index.values()) {
            if (value.size() > degress) {
                degress = value.size();
                ret = value.get(value.size() - 1) - value.get(0);
            } else if (value.size() == degress) {
                ret = Math.min(ret, value.get(value.size() - 1) - value.get(0));
            }
        }
        return ret + 1;
    }
}
