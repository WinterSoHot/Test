package cn.dx.leetcode;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnq4km/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/10
 **/
public class Rob {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // 状态：房子位置，偷不偷： 0 不偷 1 偷
        int[][] dp = new int[n][2];
        // 初始条件
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            // 不偷则选择前面一种最大值
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            // 偷只能选择前面没有偷的时候
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        // 返回最后一个偷和不偷两种的最大值
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
