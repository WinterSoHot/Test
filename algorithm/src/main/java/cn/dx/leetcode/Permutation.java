package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * https://leetcode-cn.com/problems/permutations/
 * <p>
 * 思路：回溯算法
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/1
 */
public class Permutation {
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        backtrack(nums, tmp);
        return ans;
    }

    private void backtrack(int[] nums, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int num : nums) {
            if (tmp.contains(num)) {
                continue;
            }
            tmp.add(num);
            backtrack(nums, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Permutation pm = new Permutation();
        System.out.println(pm.permute(new int[]{1, 2, 3}));
    }
}
