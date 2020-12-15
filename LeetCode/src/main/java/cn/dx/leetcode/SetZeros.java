package cn.dx.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 矩阵置零
 * <p>
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class SetZeros {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> rowZero = new HashSet<>();
        Set<Integer> colZero = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < n; j++) {
                int val = row[j];
                if (val == 0) {
                    rowZero.add(i);
                    colZero.add(j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int[] row = matrix[i];
            if (rowZero.contains(i)) {
                Arrays.fill(row, 0);
            }
            for (int j = 0; j < n; j++) {
                if (colZero.contains(j)) {
                    row[j] = 0;
                }
            }
        }
    }
}
