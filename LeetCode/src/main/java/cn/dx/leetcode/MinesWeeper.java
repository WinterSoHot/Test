package cn.dx.leetcode;

/**
 * 让我们一起来玩扫雷游戏！
 * <p>
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * <p>
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * <p>
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 注意：
 * <p>
 * 输入矩阵的宽和高的范围为 [1,50]。
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
 *
 *
 * 思路：
 * 检查点，是否为雷，周围是否需要扩展，是否直接标记数目（性能待优化）
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/20
 */
public class MinesWeeper {

    int[] xD = {0, -1, 1};
    int[] yD = {0, -1, 1};

    /**
     * 对当前位置周围雷计数
     * @param board 地图
     * @param x x位置
     * @param y y位置
     * @return 雷数目
     */
    public int countM(char[][] board, int x, int y) {
        int count = 0;
        for (int i : xD) {
            for (int j : yD) {
                int cx = x + i;
                int cy = y + j;
                if (cx < 0 || cy < 0 || cx > board.length - 1 || cy > board[0].length - 1) {
                    continue;
                }
                if (cx == x && cy == y) {
                    continue;
                }
                // 如果当前点周围有 雷存在，则为M
                if (board[cx][cy] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 判断当前位置是否为B
     * @param board 地图
     * @param x x位置
     * @param y y位置
     * @return 是否需要扩展
     */
    public boolean isB(char[][] board, int x, int y) {
        for (int i : xD) {
            for (int j : yD) {
                int cx = x + i;
                int cy = y + j;
                if (cx < 0 || cy < 0 || cx > board.length - 1 || cy > board[0].length - 1) {
                    continue;
                }
                if (cx == x && cy == y) {
                    continue;
                }
                // 如果当前点周围有 雷存在，则为M
                if (board[cx][cy] == 'M') {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        switch (board[x][y]) {
            case 'M':
                // 踩雷
                board[x][y] = 'X';
                break;
            case 'E':
                if (isB(board, x, y)) {
                    // 向周围扩展
                    board[x][y] = 'B';
                    for (int i : xD) {
                        for (int j : yD) {
                            int cx = x + i;
                            int cy = y + j;
                            if (cx < 0 || cy < 0 || cx > board.length - 1 || cy > board[0].length - 1) {
                                continue;
                            }
                            if (cx == x && cy == y) {
                                continue;
                            }
                            if (board[cx][cy] == 'E') {
                                updateBoard(board, new int[]{cx, cy});
                            }
                        }
                    }
                } else {
                    board[x][y] = (char) ('0' + countM(board, x, y));
                }
                break;
            default:
        }
        return board;
    }

    public static void main(String[] args) {
        int[] click = new int[]{3, 0};
        char[][] board = new char[][]{new char[]{'E', 'E', 'E', 'E', 'E'},
                new char[]{'E', 'E', 'M', 'E', 'E'},
                new char[]{'E', 'E', 'E', 'E', 'E'},
                new char[]{'E', 'E', 'E', 'E', 'E'}};
        MinesWeeper mw = new MinesWeeper();
//        mw.updateBoard(board,click);

        System.out.println(mw.isB(board, 3, 1));
    }
}
