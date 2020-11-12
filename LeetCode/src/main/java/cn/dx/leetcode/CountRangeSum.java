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
}
