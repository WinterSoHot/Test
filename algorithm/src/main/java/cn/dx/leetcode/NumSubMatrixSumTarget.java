package cn.dx.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 1074. 元素和为目标值的子矩阵数量
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * <p>
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * <p>
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/29
 */
public class NumSubMatrixSumTarget {
    Set<String> result = new HashSet<>();

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 二维前缀和
        int n = matrix.length, m = matrix[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int p = 1; p <= i; p++) {
                    for (int q = 1; q <= j; q++) {
                        if (sum[i][j] - sum[p - 1][j] - sum[i][q - 1] + sum[p - 1][q - 1] == target) ans++;
                    }
                }
            }
        }
        return ans;
    }
}
