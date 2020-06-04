package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
