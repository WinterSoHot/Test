package cn.dx.offer;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * <p>
 * 题目描述
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
 * 如果输入如下4 X 4矩阵：
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/8
 */
public class PrintMatrix {
    ArrayList<Integer> ans = new ArrayList<>();

    public void print(int lx, int ly, int rx, int ry, int[][] matrix) {
        for (int i = ly; i <= ry; i++) {
            ans.add(matrix[lx][i]);
        }
        for (int i = lx + 1; i <= rx; i++) {
            ans.add(matrix[i][ry]);
        }
        int h = rx - lx + 1;
        if (h > 1) {
            for (int rj = ry - 1; rj >= ly; --rj) ans.add(matrix[rx][rj]);
        }
        int w = ry - ly + 1;
        if (w > 1) {

            for (int ri = rx - 1; ri >= lx + 1; --ri) ans.add(matrix[ri][ly]);
        }

    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int lx = 0, ly = 0;
        int rx = matrix.length - 1, ry = matrix[0].length - 1;
        while (lx <= rx && ly <= ry) {
            print(lx++, ly++, rx--, ry--, matrix);
        }
        return ans;
    }
}
