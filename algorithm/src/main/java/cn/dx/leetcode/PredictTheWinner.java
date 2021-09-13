package cn.dx.leetcode;

/**
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * <p>
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/predict-the-winner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 * <p>
 * 分析：
 * 获胜结果：为先手和后手获得的分数之差，如果这个分数之差大于等于0，先手获胜，反之相反。
 * 由于每次选择从数组两端进行选择，数组因此一直是连续的。
 * <p>
 * 解法：
 * 1. 递归
 * <p>
 * 2. DP
 * 使用二维数组，二维数组的行数和列数都等于数组的长度，dp[i][j]表示当数组剩下的部分为下标i到下表j。
 * 当前玩家和另一个玩家的分数之差的最大值，注意当前玩家不一定是先手。
 * <p>
 * 只有当i<=j时，数组才有意义，i>j数组元素为0
 * i=j 数组元素为nums[i][i]
 * <p>
 * 状态转移方程
 * <p>
 * dp[i][j] = max(nums[i]-dp[i+1][j],nums[j]-dp[i][j-1])
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/1
 */
public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            // 当前dp[i][j]和i+1有关，因此i递减，先计算后面的。
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

    /**
     * 计算总分
     *
     * @param nums  数组
     * @param start 开始位置
     * @param end   结束位置
     * @param turn  当前玩家先手1还是后手-1
     * @return 返回总分
     */
    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            //数组中只剩一个元素
            return nums[start] * turn;
        }
        int totalStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int totalEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(totalStart * turn, totalEnd * turn) * turn;
    }
}
