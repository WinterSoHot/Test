package cn.dx.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 难度：困难
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/4
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<List<Integer>> res = new LinkedList<>();
        // 新区见的开始结束位置
        int startPoint = newInterval[0];
        int endPoint = newInterval[1];
        boolean flag = true;

        for (int[] interval : intervals) {
            int curStart = interval[0];
            int curEnd = interval[1];
            if (curEnd < startPoint) {
                // 当前区间在新区见之前
                res.add(Arrays.asList(curStart, curEnd));
            } else if (curStart > endPoint) {
                if (flag) {
                    // 比如把新区间或新区间融合后的区间插入
                    res.add(Arrays.asList(startPoint, endPoint));
                    flag = false;
                }
                // 当前区间在新区之后
                res.add(Arrays.asList(curStart, curEnd));
            } else {
                // 融合区间
                startPoint = Math.min(startPoint, curStart);
                endPoint = Math.max(endPoint, curEnd);
            }
        }
        if (flag) {
            // 如果新区间在最后面，没有插入到数组中，则插入
            res.add(Arrays.asList(startPoint, endPoint));
        }

        int[][] ret = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ret[i][0] = res.get(i).get(0);
            ret[i][1] = res.get(i).get(1);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{new int[]{1, 5}};
        int[] newInterval = new int[]{0, 0};
        InsertInterval ii = new InsertInterval();
        ii.insert(intervals, newInterval);
    }
}
