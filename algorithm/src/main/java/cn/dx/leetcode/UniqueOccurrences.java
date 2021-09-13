package cn.dx.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * <p>
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/28
 */
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i : arr) {
            numCount.put(i, numCount.getOrDefault(i, 0) + 1);
        }
        List<Integer> res = new LinkedList<>();
        for (Integer value : numCount.values()) {
            if (res.contains(value)) {
                return false;
            } else {
                res.add(value);
            }
        }
        return true;
    }
}
