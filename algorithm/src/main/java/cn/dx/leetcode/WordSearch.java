package cn.dx.leetcode;

/**
 *
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * https://leetcode-cn.com/problems/word-search/
 *
 * 难度：中等
 *
 * 方法：回溯/深度搜素
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/13
 */
public class WordSearch {
    private Boolean res = false;
    private int[][] visited;
    private int row;
    private int col;

    private int[] xDir = new int[]{-1, 1, 0, 0};
    private int[] yDir = new int[]{0, 0, -1, 1};

    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        visited = new int[row][col];

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (board[x][y] == word.charAt(0)) {
                    visited[x][y] = 1;
                    backtrack(board, 1, word, x, y);
                    visited[x][y] = 0;
                }
            }
        }
        return res;
    }

    private void backtrack(char[][] board, int idx, String word, int x, int y) {

        //结束条件
        if (idx == word.length()) {
            res = true;
            return;
        }

        // 扩散
        // 选择
        for (int i = 0; i < 4; i++) {
            int nx = x + xDir[i];
            int ny = y + yDir[i];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col || visited[nx][ny] == 1 || res || board[nx][ny] != word.charAt(idx)) {
                continue;
            }
            visited[nx][ny] = 1;
            backtrack(board, idx + 1, word, nx, ny);
            visited[nx][ny] = 0;
        }
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char [][] board = new char[][]{
                new char[]{'A','B','C','E'},
                new char[]{'S','F','C','S'},
                new char[]{'A','D','E','E'}};
        String word = "ABCCED";
        ws.exist(board,word);
    }
}
