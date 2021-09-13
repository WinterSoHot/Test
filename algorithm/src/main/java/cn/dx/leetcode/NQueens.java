package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * https://leetcode-cn.com/problems/n-queens/
 * <p>
 * 思路：回溯
 * <p>
 * 难度：困难
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/1
 */
public class NQueens {
    private List<List<String>> ans = new ArrayList<>();
    private StringBuilder sb;

    public List<List<String>> solveNQueens(int n) {
        List<Integer> track = new LinkedList<>();
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        backtrack(n, track);
        return ans;
    }

    /**
     * N皇后 2 返回解法个数
     *
     * @param n 面板宽高
     * @return 解法个数
     */
    public int totalNQueens(int n) {
        List<Integer> track = new LinkedList<>();
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        backtrack(n, track);
        return ans.size();
    }


    private void backtrack(int n, List<Integer> track) {
        // 结束条件，增加结果到答案中
        if (track.size() == n) {
            // 增加当前track
            List<String> item = new ArrayList<>();
            for (Integer t : track) {
                sb.setCharAt(t, 'Q');
                item.add(sb.toString());
                sb.setCharAt(t, '.');
            }
            ans.add(item);
            return;
        }

        for (int i = 0; i < n; i++) {
            // 确定当前track中没有导致皇后互相攻击
            if (track.contains(i) || check(track, i)) {
                continue;
            }
            track.add(i);
            // 递归
            backtrack(n, track);
            // 回溯
            track.remove(track.size() - 1);
        }
    }

    /**
     * 检查皇后是否能够斜着攻击
     *
     * @param track 皇后的位置信息
     * @param i     当前待放位置
     * @return 是否互相攻击
     */
    private boolean check(List<Integer> track, int i) {
        int curRow = track.size();
        for (int row = 0; row < track.size(); row++) {
            int col = track.get(row);
            if (Math.abs(curRow - row) == Math.abs(i - col)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        System.out.println(nq.solveNQueens(4));
    }
}
