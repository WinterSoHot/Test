package cn.dx.leetcode;

import java.util.*;

/**
 * 352. 将数据流变为多个不相交区间
 * 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * <p>
 * 实现 SummaryRanges 类：
 * <p>
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 *
 * @author gudongxian
 * @date 2021/10/9
 */
public class SummaryRanges {

    /**
     * 区间描述
     * key: start
     * value: end
     */
    TreeMap<Integer, Integer> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(int val) {
        // 左边
        Map.Entry<Integer, Integer> interval1 = intervals.ceilingEntry(val + 1);
        // 右边
        Map.Entry<Integer, Integer> interval0 = intervals.floorEntry(val);
        if (interval0 != null && interval0.getKey() <= val && val <= interval0.getValue()) {
            // 当前数字以及包含在区间
            return;
        } else {
            // 左边区间是否和当前val相邻
            boolean leftAside = interval0 != null && interval0.getValue() + 1 == val;
            boolean rightAside = interval1 != null && interval1.getKey() - 1 == val;
            if (leftAside && rightAside) {
                int left = interval0.getKey(), right = interval1.getValue();
                intervals.remove(interval0.getKey());
                intervals.remove(interval1.getKey());
                intervals.put(left, right);
            } else if (rightAside) {
                Integer right = interval1.getValue();
                intervals.remove(interval1.getKey());
                intervals.put(val, right);
            } else if (leftAside) {
                Integer left = interval0.getKey();
                intervals.remove(interval0.getKey());
                intervals.put(left, val);
            } else {
                intervals.put(val, val);
            }
        }
    }

    public int[][] getIntervals() {
        int[][] ans = new int[intervals.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            ans[index][0] = entry.getKey();
            ans[index][1] = entry.getValue();
            index++;
        }
        return ans;
    }
}
