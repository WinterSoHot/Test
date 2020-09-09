package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 *     所有数字（包括 target）都是正整数。
 *     解集不能包含重复的组合。
 *
 * https://leetcode-cn.com/classic/problems/combination-sum/description/
 *
 * 难度：中
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 回溯 + 剪枝
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        Arrays.sort(candidates);
        DFS(candidates, len, target, 0, new ArrayDeque<>(), res);
        return res;
    }

    private void DFS(int[] candidates, int len, int target, int begin, ArrayDeque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            // 全局使用了一个Path，因此拷贝一份保存
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (candidates[i] > target) {
                // 因为排序过后，如果当前都无法满足，后面数字更大明显无法满足。
                break;
            }
            path.add(candidates[i]);
            DFS(candidates, len, target - candidates[i], i, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {

    }
}
