package cn.dx.leetcode;

/**
 * 1004. 最大连续1的个数 III
 * <p>
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * <p>
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/19
 */
public class MaxOneConsectiveIII {
    public int longestOnes(int[] A, int K) {
        int len = A.length;
        int left = 0, right = 0;
        int[] num4count = new int[2];
        while (right < len) {
            num4count[A[right]]++;
            if (num4count[0] > K) {
                num4count[A[left]]--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
