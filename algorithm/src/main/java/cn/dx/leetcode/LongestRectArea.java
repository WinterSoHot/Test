package cn.dx.leetcode;

import java.util.Stack;

/**
 * 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/8
 **/
public class LongestRectArea {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i <= len; i++) {
            int cur = i == len ? -1 : heights[i];
            while (!stack.isEmpty() && cur <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : (stack.peek() + 1);
                int right = i - 1;
                int erea = height * (right - left + 1);
                result = Math.max(result, erea);
            }
            stack.push(i);
        }
        return result;
    }
}
