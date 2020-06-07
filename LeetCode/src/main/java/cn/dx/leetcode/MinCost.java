package cn.dx.leetcode;

/**
 * https://leetcode-cn.com/contest/weekly-contest-192/problems/paint-house-iii/
 * DP
 */
public class MinCost {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int INF = 100000000;
        int [][][] f = new int[111][22][111];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= target; k++) {
                    f[i][j][k] = INF;
                }
            }
        }
        f[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= target; k++) {
                    if (f[i][j][k] == INF) continue;
                    if (houses[i] != 0){
                        int x = houses[i];
                        f[i+1][x][k+(x != j ? 1 : 0)] =  Math.min(f[i+1][x][k+(x != j ? 1 : 0)],f[i][j][k]);
                    }else {
                        for (int x = 1; x <= n; x++) {
                            f[i+1][x][k+(x != j ? 1 : 0)] =  Math.min(f[i+1][x][k+(x != j ? 1 : 0)],f[i][j][k] + cost[i][x-1]);
                        }
                    }
                }
            }
        }
        int ret = INF;
        for (int j = 1; j <=n ; j++) {
            ret = Math.min(ret,f[m][j][target]);
        }
        if (ret == INF) return -1;
        return ret;
    }


}
