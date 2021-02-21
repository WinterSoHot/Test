package cn.dx.leetcode.biweekly.c46;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 5671. 地图中的最高点
 * <p>
 * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
 * <p>
 * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
 * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
 * 你需要按照如下规则给每个单元格安排高度：
 * <p>
 * 每个格子的高度都必须是非负的。
 * 如果一个格子是是 水域 ，那么它的高度必须为 0 。
 * 任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
 * <p>
 * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class MapOfHighestPeak {
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    int row, col;

    public int[][] highestPeak(int[][] isWater) {
        row = isWater.length;
        col = isWater[0].length;
        Deque<Integer> qX = new ArrayDeque<>();
        Deque<Integer> qY = new ArrayDeque<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isWater[i][j] == 1) {
                    isWater[i][j] = 0;
                    qX.offer(i);
                    qY.offer(j);
                } else {
                    isWater[i][j] = -1;
                }
            }
        }
        while (!qX.isEmpty()) {
            int size = qX.size();
            for (int i = 0; i < size; i++) {
                Integer x = qX.poll();
                Integer y = qY.poll();
                for (int k = 0; k < 4; k++) {
                    int nX = x + dx[k];
                    int nY = y + dy[k];
                    if (nX >= 0 && nX < row && nY >= 0 && nY < col) {
                        if (isWater[nX][nY] == -1) {
                            isWater[nX][nY] = isWater[x][y] + 1;
                            qX.offer(nX);
                            qY.offer(nY);
                        } else {
                            isWater[nX][nY] = Math.min(isWater[x][y] + 1, isWater[nX][nY]);
                        }
                    }
                }
            }
        }
        return isWater;
    }


}
