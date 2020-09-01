package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/1
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return dp(coins,amount);
    }

    /**
     * 备忘录
     */
    private Map<Integer,Integer> memo = new HashMap<>();

    private int dp(int[] coins, int amount) {
        if (memo.containsKey(amount)){
            return memo.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(coins, amount - coin);
            if (subProblem == -1){
                continue;
            }
            res = Math.min(res,1+ subProblem);
        }
        memo.put(amount,res == Integer.MAX_VALUE ? -1 : res);
        return memo.get(amount);
    }

}
