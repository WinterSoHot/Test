package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * https://leetcode-cn.com/problems/surrounded-regions/
 * 超时
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/11
 */
public class Solve {

    private List<String> loc = new ArrayList<>();
    private int xLocMax, yLocMax;
    private int xLocMin, yLocMin;
    private int row;
    private int col;

    /**
     * 将为'0'的合并成团
     *
     * @param x     当前x坐标
     * @param y     当前y坐标
     * @param board 数据
     */
    public void searchCluster(int x, int y, char[][] board) {

        if (x < 0 || y < 0 || x > row || y > col || board[x][y] != 'O') {
            return;
        }
        // 当前点
        String locStr = x +"-"+y;
        if (!loc.contains(locStr)) {
            loc.add(locStr);
            if (x < xLocMin) {
                xLocMin = x;
            }
            if (x > xLocMax) {
                xLocMax = x;
            }

            if (y < yLocMin) {
                yLocMin = y;
            }
            if (y > yLocMax) {
                yLocMax = y;
            }
            searchCluster(x - 1, y, board);
            searchCluster(x, y - 1, board);
            searchCluster(x + 1, y, board);
            searchCluster(x, y + 1, board);
        }
    }


    public void solve(char[][] board) {

        int w, h;
        w = board.length;
        if (w == 0) {
            return;
        }
        h = board[0].length;

        row = board.length - 1;
        col = board[0].length - 1;

        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {
                char ch = board[i][j];
                if (ch != 'O') {
                    continue;
                }
                // 检查上方和左方是否为'X'
                char leftCh = board[i][j - 1];
                char upCh = board[i - 1][j];
                if (leftCh != 'X' || upCh != 'X') {
                    continue;
                }
                //检查右方和下方
                xLocMin = i;
                xLocMax = i;
                yLocMin = j;
                yLocMax = j;
                searchCluster(i, j, board);
                if (xLocMin > 0 && xLocMax < w - 1 && yLocMin > 0 && yLocMax < h - 1) {
                    for (String itemLoc : loc) {
                        String[] split = itemLoc.split("-");
                        board[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = 'X';
                    }
                }
                loc.clear();
                i = xLocMax;
            }
        }
    }

    public static void main(String[] args) {
        Solve s = new Solve();
        char[][] board = new char[4][4];
        board[0] = new char[]{'X', 'X', 'X', 'X'};
        board[1] = new char[]{'X', 'O', 'O', 'X'};
        board[2] = new char[]{'X', 'X', 'O', 'X'};
        board[3] = new char[]{'X', 'O', 'X', 'X'};

        char[][] board2 = new char[5][5];
        board2[0] = new char[]{'O', 'X', 'X', 'O', 'X'};
        board2[1] = new char[]{'X', 'O', 'O', 'X', 'O'};
        board2[2] = new char[]{'X', 'O', 'X', 'O', 'X'};
        board2[3] = new char[]{'O', 'X', 'O', 'O', 'O'};
        board2[4] = new char[]{'X', 'X', 'O', 'X', 'O'};

        s.solve(board2);
        for (char[] chars : board2) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        s.searchCluster(1, 1, board2);
        System.out.println(s.loc);
    }
}
