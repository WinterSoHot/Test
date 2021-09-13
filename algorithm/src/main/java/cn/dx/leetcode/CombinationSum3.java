package cn.dx.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 *找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 *     所有数字都是正整数。
 *     解集不能包含重复的组合。
 *
 * 难度：中等
 *
 * 方法：回溯+剪枝
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/11
 */
public class CombinationSum3 {
    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k,n,1);
        return ans;
    }

    /**
     * 回溯
     * @param k 剩余可选数字个数
     * @param target 剩余目标大小
     * @param start 开始寻找当前数字的位置
     */
    private void backtrack(int k, int target,int start) {
        // 结束条件
        if (target == 0 && k == 0){
            // 重复列表比较，还有优化空间
            for (List<Integer> item : ans) {
                if (Arrays.equals(item.toArray(), path.stream().sorted().toArray())) {
                    return;
                }
            }
            ans.add(new ArrayList<>(path.stream().sorted().collect(Collectors.toList())));
            return;
        }
        // 不可以再选数字了
        if (k == 0){
            return;
        }

        // 扩散
        for (int i = start; i < 10; i++) {
            // 筛选选择列表
            if (path.contains(i)){
                continue;
            }
            // 选择
            path.add(i);
            // 递归
            backtrack(k - 1,target  - i,i+1);
            // 撤销
            path.removeLast();
        }
    }
}
