package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.12. 八皇后
 * <p>
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/9
 */
public class SolveNQueens {
    List<List<String>> ans;
    List<Integer> path;
    StringBuilder sb = new StringBuilder();

    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        path = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        backtrack(n, path);
        return ans;
    }

    private void backtrack(int n, List<Integer> path) {
        if (path.size() == n) {
            List<String> item = new ArrayList<>();
            for (Integer t : path) {
                sb.setCharAt(t, 'Q');
                item.add(sb.toString());
                sb.setCharAt(t, '.');
            }
            ans.add(item);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (check(i, path)) {
                continue;
            }
            path.add(i);
            backtrack(n, path);
            path.remove(path.size() - 1);
        }
    }

    private boolean check(int i, List<Integer> path) {
        if (path.contains(i)) {
            // 同一个列
            return true;
        }
        for (int row = 0; row < path.size(); row++) {
            if (Math.abs(row - path.size()) == Math.abs(path.get(row) - i)) {
                return true;
            }
        }
        return false;
    }
}
