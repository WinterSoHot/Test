package cn.dx.leetcode;

/**
 * 85. 最大矩形
 * <p>
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * level: hard
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/26
 **/
public class MaxRect {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        // 标记当前坐标，左边为1的数量
        int[][] left = new int[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (matrix[x][y] == '1') {
                    if (y == 0) {
                        left[x][y] = 1;
                    } else {
                        left[x][y] = left[x][y - 1] + 1;
                    }
                }
            }
        }
        int ret = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (matrix[x][y] == '0') {
                    continue;
                }

                // 当前高为1,宽为左边1的数量
                int width = left[x][y];
                int area = width;
                // 增加高
                for (int k = x - 1; k >= 0; k--) {
                    // 选择最小的宽
                    width = Math.min(width, left[k][y]);
                    // 重新计算 高 × 宽
                    area = Math.max(area, (x - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
}
