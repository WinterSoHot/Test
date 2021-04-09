package cn.dx;

import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/7
 */
public class Main12 {

    static int res = -1;
    static int row = -1;
    static int col = -1;

    static int[] dx = new int[]{0, 1};
    static int[] dy = new int[]{1, 0};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        row = in.nextInt();
        col = in.nextInt();
        int t = in.nextInt();
        int[][] board = new int[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                board[x][y] = in.nextInt();
            }
        }
        dfs(board, t - board[0][0], 0, 0, board[0][0]);
        System.out.println(res);
    }

    private static void dfs(int[][] board, int t, int x, int y, int time) {
        if (t < 0) {
            return;
        }
        if (x == row - 1 && y == col - 1) {
            res = Math.max(res, time);
            return;
        }
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                dfs(board, t - board[nx][ny], nx, ny, time + board[nx][ny]);
            }
        }
    }
}
