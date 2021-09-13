package cn.dx.leetcode;

/**
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * <p>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: hard
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/12/2
 */
public class CreateMaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            // 分别计算两个数组长度为i和k-i的最大子序列
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);

            // 合并子序列
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            // 两个子序列比较
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    /**
     * 计算nums数组中得到长度为k的最大子序列
     *
     * @param nums 数组
     * @param k    子序列长度
     * @return 最大子序列
     */
    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        // 单调栈
        int[] stack = new int[k];

        int top = -1;
        int remain = length - k;
        for (int num : nums) {
            // 当前元组小于栈顶元素，当前需要出栈的元素大于0，则出栈
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            // 入栈
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                // 不加入直接放弃
                remain--;
            }
        }
        return stack;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            // 比较，那个大就将大的增加上去
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    /**
     * 两个序列从index1和index2开始比较
     *
     * @param subsequence1
     * @param index1
     * @param subsequence2
     * @param index2
     * @return
     */
    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            // 在长度范围内循环

            // 比较
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                // 返回结果
                return difference;
            }
            // 同时后移
            index1++;
            index2++;
        }
        // 长度条件不满足，比较剩余长度
        return (x - index1) - (y - index2);
    }
}
