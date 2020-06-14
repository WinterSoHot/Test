package cn.dx.leetcode;

import java.util.Arrays;

/**
 * FinalPrices https://leetcode-cn.com/contest/biweekly-contest-28/problems/final-prices-with-a-special-discount-in-a-shop/
 *
 * @author dongxian
 * @version 1.0
 * 20-6-13 下午10:31
 **/
public class FinalPrices {
    public int[] finalPrices(int[] prices) {
        int[] res = Arrays.copyOf(prices, prices.length);
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]){
                    res[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return res;
    }
}
