package cn.dx.leetcode;

/**
 * 买卖股票的最佳时机
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn8fsh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/4
 **/
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            int cur = prices[i];
            dp[i] = Math.max(dp[i - 1], cur - min);
            if (cur < min) {
                min = cur;
            }
        }
        return dp[n - 1];
    }
}
