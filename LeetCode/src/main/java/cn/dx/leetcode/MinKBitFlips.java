package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 995. K 连续位的最小翻转次数
 * <p>
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 * <p>
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/18
 */
public class MinKBitFlips {
    /**
     * 模拟 超时 通过 98/110
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int count = 0;
        int len = A.length;

        int left = 0, right;
        for (left = 0; left <= len - K; left++) {
            if (A[left] == 0) {
                right = left + K - 1;
                for (int i = left; i <= right; i++) {
                    A[i] = A[i] == 0 ? 1 : 0;
                }
                count++;
            }
        }
        System.out.println(Arrays.toString(A));
        for (int i = len - 1; i >= len - K; i--) {
            if (A[i] == 0) {
                return -1;
            }
        }
        return count;
    }

    /**
     * 官方答案
     * <p>
     * 使用差分数组记录当前位置的变化次数
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips1(int[] A, int K) {
        int count = 0;
        int len = A.length;
        // 差分数组 表示 当前位置i i-1 到 i 变化的次数
        int[] diff = new int[len + 1];
        int s = 0;
        for (int i = 0; i < len; i++) {
            s += diff[i];
            // 当前 A[i] 是 0
            if ((A[i] + s) % 2 == 0) {
                if (i + K > len) {
                    return -1;
                }
                // 修改次数加1 则 i ~ i + K - 1修改次数都加1
                s++;
                count++;
                // 印象i+K变化次数-1
                diff[i + K]--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MinKBitFlips mf = new MinKBitFlips();
        System.out.println(mf.minKBitFlips1(new int[]{0, 1, 0}, 1));
        System.out.println(mf.minKBitFlips1(new int[]{1, 1, 0}, 2));
        System.out.println(mf.minKBitFlips1(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }
}
