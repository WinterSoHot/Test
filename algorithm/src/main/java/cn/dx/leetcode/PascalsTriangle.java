package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/6
 **/
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> pre = new LinkedList<>();
        pre.add(1);
        res.add(pre);

        for (int i = 1; i < numRows; i++) {
            List<Integer> cur = new LinkedList<>();
            cur.add(1);
            for (int j = 1; j < i; j++) {
                cur.add(pre.get(j - 1) + pre.get(j));
            }
            cur.add(1);
            res.add(cur);
            pre = cur;
        }
        return res;
    }
}
