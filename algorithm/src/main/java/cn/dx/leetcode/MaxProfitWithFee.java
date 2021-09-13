package cn.dx.leetcode;

/**
 * 动态规划
 * <p>
 * 状态：当前是否买股票
 * <p>
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/17
 **/
public class MaxProfitWithFee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 天数，是否买股票 0 未买股票 1 买了股票
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 当前不买股票 = 前一天不买股票 和 买了股票的最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            // 当天买股票 = 前一天买股票 和 前一天不买股票今天买的最大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
