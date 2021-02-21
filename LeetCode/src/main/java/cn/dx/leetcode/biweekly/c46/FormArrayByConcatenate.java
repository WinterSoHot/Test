package cn.dx.leetcode.biweekly.c46;

/**
 * 5669. 通过连接另一个数组的子数组得到一个数组
 * <p>
 * 给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。
 * <p>
 * 你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。（也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
 * <p>
 * 如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。
 * <p>
 * 如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class FormArrayByConcatenate {
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = groups.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int[] group = groups[i];
            start = find(nums, group, start);
            if (start == -1) {
                return false;
            }
        }
        return true;
    }

    private int find(int[] nums, int[] group, int start) {
        int len = group.length;
        for (int i = start; i < nums.length - len + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if (nums[i + j] != group[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i + len;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FormArrayByConcatenate fab = new FormArrayByConcatenate();
        int[][] groups = new int[][]{
                new int[]{1, -1, -1},
                new int[]{3, -2, 0}
        };
        int[] nums = {1, -1, 0, 1, -1, -1, 3, -2, 0};
        System.out.println(fab.canChoose(groups, nums));
    }
}
