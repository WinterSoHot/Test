package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * https://leetcode-cn.com/problems/permutations-ii/
 * <p>
 * 难度：中等
 * 方法： 回溯
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/18
 */
public class PermuteUnique {
    List<List<Integer>> ans = new ArrayList<>();
    int[] visited;
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 标记是否以及被选择
        visited = new int[nums.length];
        backtrack(nums);
        return ans;
    }

    private void backtrack(int[] nums) {
        if (path.size() == nums.length) {
            // 防止重复
            for (List<Integer> hasAns : ans) {
                if (hasAns.equals(path)) {
                    return;
                }
            }
            ans.add(new ArrayList<>(path));
            return;
        }
        // 扩散
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            // 选择
            visited[i] = 1;
            path.add(nums[i]);
            // 递归
            backtrack(nums);
            // 撤销
            visited[i] = 0;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        PermuteUnique pu = new PermuteUnique();
        System.out.println(pu.permuteUnique(new int[]{1, 1, 2}));
    }
}
