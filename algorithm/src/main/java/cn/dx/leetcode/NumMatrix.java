package cn.dx.leetcode;

class NumMatrix {

    private final int[][] mat;
    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        mat = matrix;
        int row = matrix.length;
        if (row == 0) {
            return;
        }
        int col = matrix[0].length;
        sum = new int[row][col];
        sum[0][0] = mat[0][0];
        for (int i = 1; i < row; i++) {
            sum[i][0] = sum[i - 1][0] + mat[i][0];
        }
        for (int i = 1; i < col; i++) {
            sum[0][i] = sum[0][i - 1] + mat[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                sum[i][j] = mat[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int v = sum[row2][col2];
        if (col1 > 0) {
            v -= sum[row2][col1 - 1];
        }
        if (row1 > 0) {
            v -= sum[row1 - 1][col2];
        }
        if (row1 > 0 && col1 > 0) {
            v += sum[row1 - 1][col1 - 1];
        }
        return v;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{3, 0, 1, 4, 2},
                new int[]{5, 6, 3, 2, 1},
                new int[]{1, 2, 0, 1, 5},
                new int[]{4, 1, 0, 1, 7},
                new int[]{1, 0, 3, 0, 5}
        };
        NumMatrix nm = new NumMatrix(matrix);
        System.out.println(nm.sumRegion(2, 1, 4, 3));
        System.out.println(nm.sumRegion(1, 1, 2, 2));
        System.out.println(nm.sumRegion(1, 2, 2, 4));
    }
}