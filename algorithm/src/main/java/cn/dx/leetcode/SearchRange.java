package cn.dx.leetcode;

/**
 *
 *
 *给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 *你的算法时间复杂度必须是 O(log n) 级别。
 *
 *如果数组中不存在目标值，返回 [-1, -1]。
 *
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/3
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        boolean flag = false;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                flag = true;
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (flag) {
            int[] res = new int[2];
            int tmp = mid;
            while (tmp >= 0 && nums[tmp] == target) {
                tmp--;
            }
            res[0] = tmp + 1;
            tmp = mid;
            while (tmp < nums.length && nums[tmp] == target) {
                tmp++;
            }
            res[1] = tmp - 1;
            return res;
        } else {
            return new int[]{-1, -1};
        }
    }
}
