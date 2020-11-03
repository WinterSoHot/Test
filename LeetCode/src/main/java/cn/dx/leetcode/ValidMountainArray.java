package cn.dx.leetcode;

/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * <p>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * <p>
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/3
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int flag = 0;
        int pre = 0;
        int left = 0;
        for (int i : A) {
            if (flag == 0) {
                flag = 1;
                pre = i;
            } else if (flag == 1) {
                if (i > pre) {
                    left++;
                    pre = i;
                } else if (i < pre) {
                    if (left == 0) {
                        return false;
                    }
                    flag = -1;
                    pre = i;
                } else {
                    return false;
                }
            } else if (flag == -1) {
                if (i < pre) {
                    pre = i;
                } else {
                    return false;
                }
            }
        }
        return flag == -1;
    }
}
