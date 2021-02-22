package cn.dx.leetcode;

/**
 * 766. 托普利茨矩阵
 * <p>
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * <p>
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/22
 */
public class ToeplitzMatrix {
    int row;
    int col;

    public boolean isToeplitzMatrix(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (!isSame(i, 0, matrix)) {
                return false;
            }
        }
        for (int i = 1; i < col; i++) {
            if (!isSame(0, i, matrix)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSame(int x, int y, int[][] matrix) {
        int cur = matrix[x][y];
        int nx = x + 1;
        int ny = y + 1;
        while (nx >= 0 && ny >= 0 && nx < row && ny < col) {
            if (matrix[nx][ny] != cur) {
                return false;
            }
            nx++;
            ny++;
        }
        return true;
    }
}
