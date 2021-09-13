package cn.dx.leetcode;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/30
 */
public class IslandPerimeter {

    private int row;
    private int column;

    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    public int islandPerimeter(int[][] grid) {
        row = grid.length;
        column = grid[0].length;
        int perimeter = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (grid[x][y] == 1) {
                    perimeter += check(grid, x, y);
                }
            }
        }
        return perimeter;
    }

    /**
     * 检查四周，默认四条边，靠近一个石头减一
     *
     * @param grid 网格
     * @param x    当前位置x
     * @param y    当前位置y
     * @return 边
     */
    private int check(int[][] grid, int x, int y) {
        int count = 4;
        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            if (cx >= 0 && cx < row && cy >= 0 && cy < column && grid[cx][cy] == 1) {
                count--;
            }
        }
        return count;
    }
}
