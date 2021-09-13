package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: hard
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/12/28
 */
public class BestTimeToSellAndBuyIV {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        // 设定两个数据：第一个维度 当前天 第二个维度 交易次数
        // buy 进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润
        // sell 恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润。
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        // base case
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        // 标记没有操作
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                // 状态转移
                // 买 = Max(前一天，卖出去 - 当天price)
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                // 卖 = Max(前一天，前一天买 + 当前交易)
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }
}
