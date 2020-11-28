package cn.dx.leetcode;

/**
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-range-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：困难
 * <p>
 * 超时
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/7
 */
public class CountRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int ret = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = 0;
                for (int c = i; c < j; c++) {
                    sum += nums[c];
                }
                if (sum >= lower && sum <= upper) {
                    ret++;
                }
            }
        }
        return ret;
    }

    public int countRangeSum2(int[] nums, int lower, int upper) {
        long s = 0;
        // 前缀和
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        // 二分查找
        if (left == right) {
            return 0;
        } else {
            // 中间点
            int mid = (left + right) / 2;
            // 左半边 满足区间和得个数
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            // 右半边 满足区间和得个数
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;
            // 求当前区间满足区间和得个数

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                // l 右移，当前i到l区间小于low
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                // r 右移， 当前i到r小于upper
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }
}
