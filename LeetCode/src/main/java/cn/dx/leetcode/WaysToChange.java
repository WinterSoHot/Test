package cn.dx.leetcode;

public class WaysToChange {
    public static void main(String[] args) {
        // 动态规划
        // https://leetcode-cn.com/problems/coin-lcci/

        int n = 29;
        final int[] coins = {25, 10, 5, 1};
        final int mod = 1000000007;
//            优化版本
//        int[] fun = new int[n + 1];
//        fun[0] = 1;
//        for (int c = 0; c < coins.length; c++) {
//            int coin = coins[c];
//            for (int i = coin; i <= n; i++) {
//                fun[i] = (fun[i] + fun[i - coin]) % mod;
//            }
//
//        }
//        System.out.println(fun[n]);
        int[][] fun = new int[4][n + 1];
        for (int i = 0; i < 4; i++) {
            fun[i][0] = 1;
        }

        for (int i = 0; i < 4; i++) {
            int coin = coins[i];
            for (int j = coin; j <= n; j++) {
                if (i == 0) {
                    fun[i][j] = fun[i][j - coin] % mod;
                    continue;
                }
                fun[i][j] = (fun[i - 1][j] + fun[i][j - coin]) % mod;
            }
        }
        System.out.println(String.format("结果：%d", fun[3][n]));

        System.out.println("动态规划转移矩阵：");
        System.out.print("\t\t\t");
        for (int i = 0; i <= n; i++) {
            System.out.print(String.format("%d\t", i));
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.print(String.format("coin: %d:\t", coins[i]));
            for (int j = 0; j <= n; j++) {
                System.out.print(String.format("%d\t", fun[i][j]));
            }
            System.out.println();
        }
    }
}
