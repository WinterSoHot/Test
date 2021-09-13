package cn.dx.leetcode;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvtsnm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 深度优先搜索
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/8
 **/
public class NumOfLand {
    int row, col;

    /**
     * 移动方向
     */
    int[] dirX = new int[]{-1, 0, 1, 0};
    int[] dirY = new int[]{0, -1, 0, 1};

    public int numIslands(char[][] grid) {
        int ret = 0;
        row = grid.length;
        col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    deepFirstSearch(grid, i, j);
                    ret++;
                }
            }
        }
        return ret;
    }

    private void deepFirstSearch(char[][] grid, int i, int j) {
        // 修改状态
        grid[i][j] = '2';
        // 移动
        for (int index = 0; index < 4; index++) {
            int newI = i + dirX[index];
            int newJ = j + dirY[index];

            // 是否满足连接条件
            if (newI < 0 || newJ < 0 || newI >= row || newJ >= col || grid[newI][newJ] != '1') {
                continue;
            }
            deepFirstSearch(grid, newI, newJ);
        }
    }

    public static void main(String[] args) {
        NumOfLand nl = new NumOfLand();
        char[][] grid = new char[][]{
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '1', '0', '0'},
                new char[]{'0', '0', '0', '1', '1'}
        };
        int ret = nl.numIslands(grid);
        System.out.println(ret);
        for (char[] chars : grid) {
            for (char ch : chars) {
                System.out.print(ch + "\t");
            }
            System.out.println();
        }
    }
}
