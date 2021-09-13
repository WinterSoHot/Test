package cn.dx.leetcode;

/**
 * 896. 单调数列
 * <p>
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/28
 */
public class MonotonicArray {
    public boolean isMonotonic(int[] A) {
        int n = A.length;
        if (n < 2) {
            return true;
        }
        int flag = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] == A[i - 1]) {
                continue;
            } else if (A[i] > A[i - 1]) {
                if (flag == -1) {
                    return false;
                }
                flag = 1;
            } else {
                if (flag == 1) {
                    return false;
                }
                flag = -1;
            }
        }
        return true;
    }
}
