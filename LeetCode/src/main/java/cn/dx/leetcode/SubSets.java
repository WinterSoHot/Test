package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集
 * <p>
 * https://leetcode-cn.com/problems/subsets/
 * <p>
 * 难度：中等
 * <p>
 * 方法：回溯
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/20
 */
public class SubSets {

    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> path = new LinkedList<>();
    int[] visited;

    public List<List<Integer>> subsets(int[] nums) {
        visited = new int[nums.length];
        backtrack(nums, 0);
        return ans;
    }

    /**
     * 由于时排列，不需要往前面选
     *
     * @param nums  数组
     * @param start 开始选择位置
     */
    private void backtrack(int[] nums, int start) {
        ans.add(new ArrayList<>(path));
        if (path.size() == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            // 选择
            visited[i] = 1;
            path.add(nums[i]);
            backtrack(nums, i + 1);
            // 撤回
            path.removeLast();
            visited[i] = 0;
        }
    }
}
