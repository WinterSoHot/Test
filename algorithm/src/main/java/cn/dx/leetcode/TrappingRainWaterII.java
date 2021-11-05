package cn.dx.leetcode;

/**
 * 407. 接雨水 II
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 * @author gudongxian
 * @date 2021/11/3
 */
public class TrappingRainWaterII {

    public int trapRainWater(int[][] heightMap) {
        int ans = 0;
        // 当前格子能放的水 water = min(max(left), max(right), max(up), max(down))
        int m = heightMap.length;
        if (m == 0) {
            return ans;
        }
        int n = heightMap[0].length;
        int[][] aroundMinMaxHeights = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int leftHeight = heightMap[i][0];
                int rightHeight = heightMap[i][n - 1];

                for (int k = 0; k < j; k++) {
                    leftHeight = Math.max(leftHeight, heightMap[i][k]);
                }
                for (int k = j + 1; k < n; k++) {
                    rightHeight = Math.max(rightHeight, heightMap[i][k]);
                }

                int upHeight = heightMap[0][j];
                int downHeight = heightMap[m - 1][j];
                for (int k = 0; k < i; k++) {
                    upHeight = Math.max(upHeight, heightMap[k][j]);
                }

                for (int k = i + 1; k < m; k++) {
                    downHeight = Math.max(downHeight, heightMap[k][j]);
                }
                aroundMinMaxHeights[i][j] = Math.min(Math.min(Math.min(leftHeight, rightHeight), upHeight), downHeight);
            }
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                ans += Math.max(0, aroundMinMaxHeights[i][j] - heightMap[i][j]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        TrappingRainWaterII it = new TrappingRainWaterII();
        long start = System.currentTimeMillis();
        int ans = it.trapRainWater(new int[][]{
                new int[]{1, 4, 3, 1, 3, 2},
                new int[]{3, 2, 1, 3, 2, 4},
                new int[]{2, 3, 3, 2, 3, 1}
        });
        long end = System.currentTimeMillis();
        System.out.println("ans = " + ans);
        System.out.println("time = " + (end - start));
    }
}
