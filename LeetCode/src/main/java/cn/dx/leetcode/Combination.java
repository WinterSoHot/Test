package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * https://leetcode-cn.com/classic/problems/combinations/description/
 *
 *
 * 难度：中等
 *
 * 方法：回溯
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/8
 */
public class Combination {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1,n,k);
        return ans;
    }

    /**
     * 通过preIdx 和 n限制选择的范围 [preIdx,n]
     * @param preIdx 开始选择的位置
     * @param n 结束选择的位置
     * @param k 选几个数
     */
    private void backtrack(int preIdx, int n, int k) {
        // 结束条件
        if (tmp.size() == k){
            ans.add(new ArrayList<>(tmp));
            return;
        }

        // 遍历选择列表
        for (int i = preIdx; i <= n; i++) {

            // 选择
            tmp.add(i);
            // 迭代
            backtrack(i+1,n,k);
            // 撤销选择
            tmp.remove(tmp.size()-1);
        }
    }
}
