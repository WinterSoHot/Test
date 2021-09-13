package cn.dx.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 740. 删除并获得点数
 * TODO 未测试通过
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/6
 */
public class DeleteAndEarn {

    int res;
    Map<Integer, Integer> mustDelete = new HashMap<>();

    public int deleteAndEarn(int[] nums) {
        boolean[] hasDeleted = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, hasDeleted, 0);
        return res;
    }

    private void backtrack(int[] nums, boolean[] hasDeleted, int cur) {
        res = Math.max(res, cur);
        for (int i = 0; i < nums.length; i++) {
            if (mustDelete.containsKey(nums[i])) {
                hasDeleted[i] = true;
            }
            if (hasDeleted[i]) {
                continue;
            }
            cur += nums[i];
            hasDeleted[i] = true;
            mustDelete.put(nums[i] - 1, mustDelete.getOrDefault(nums[i] - 1, 0) + 1);
            mustDelete.put(nums[i] + 1, mustDelete.getOrDefault(nums[i] + 1, 0) + 1);
            backtrack(nums, hasDeleted, cur);
            hasDeleted[i] = false;
            mustDelete.put(nums[i] - 1, mustDelete.getOrDefault(nums[i] - 1, 0) - 1);
            if (mustDelete.get(nums[i] - 1) == 0) {
                mustDelete.remove(nums[i] - 1);
            }
            mustDelete.put(nums[i] + 1, mustDelete.getOrDefault(nums[i] + 1, 0) - 1);
            if (mustDelete.get(nums[i] + 1) == 0) {
                mustDelete.remove(nums[i] + 1);
            }
        }
    }

    public static void main(String[] args) {
        DeleteAndEarn dae = new DeleteAndEarn();
        System.out.println(dae.deleteAndEarn(new int[]{3, 4, 2}));
    }
}
