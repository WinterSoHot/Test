package cn.dx.leetcode;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 难度：中等
 * <p>
 * 动态规划  0-1背包
 * <p>
 * 参考题解：
 * 本题是经典的NP 完全问题，也就是说，如果你发现了该问题的一个多项式算法，那么恭喜你证明出了 P=NP，可以期待一下图灵奖了。
 * <p>
 * 正因如此，我们不应期望该问题有多项式时间复杂度的解法。我们能想到，
 * 例如基于贪心算法的「将数组降序排序后，依次将每个元素添加至当前元素和较小的子集中」之类的方法都是错误的，
 * 可以轻松地举出反例。因此，我们必须尝试非多项式时间复杂度的算法，例如时间复杂度与元素大小相关的动态规划。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/11
 */
public class PartitionEqualSubsetSum {


    public boolean canPartition(int[] nums) {
        int n = nums.length;

        // 过滤不可能成立的情况
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        // 建立dp数组， n 行 target + 1 列，0-1问题，dp[i][j]表示是否能够再数组的[0,i]中找出多个元素满足和等于  j
        boolean[][] dp = new boolean[n][target + 1];

        // 初始条件，当target为0，所有情况都满足
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // 数组第一个元素，只能选择第一个
        dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            // 一行一行往下规划
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                // j 当前目标值
                if (j < num) {
                    //如果当前的j小于num，则表示当前的肯定不能选择
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 两种情况，当前不选择 和 选择当前，两则的或作为结果表示 dp[i][j] 是否能够满足
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                }
            }
        }
        return dp[n - 1][target];
    }
}
