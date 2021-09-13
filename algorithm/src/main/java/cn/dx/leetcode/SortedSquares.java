package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/16
 */
public class SortedSquares {
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = Math.abs(A[i]);
        }
        Arrays.sort(res);
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i] * res[i];
        }
        return res;
    }
}
