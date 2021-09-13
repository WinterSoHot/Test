package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 *     所有数字（包括目标数）都是正整数。
 *     解集不能包含重复的组合。
 *
 * https://leetcode-cn.com/classic/problems/combination-sum-ii/description/
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/10
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 回溯 + 剪枝
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        Arrays.sort(candidates);
        backtrack(candidates, len, target, 0, new ArrayDeque<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int len, int target, int begin, ArrayDeque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            // 检查当前path是否以及存在
            for (List<Integer> item : res) {
                if (Arrays.equals(item.toArray(),path.toArray())){
                    return;
                }
            }
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
            backtrack(candidates, len, target - candidates[i], i+1, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSum2 cs2 = new CombinationSum2();
        System.out.println(cs2.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}
                , 8));
    }
}
