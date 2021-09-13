package cn.dx.bullcode;

/**
 * 题目描述
 * 请实现有重复数字的有序数组的二分查找。
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 * <p>
 * https://www.nowcoder.com/practice/7bc4a1c7c371425d9faa9d1b511fe193?tpId=188&&tqId=35175&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
 *
 * 通过率：93.3%
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/3
 */
public class UpperBound {
    /**
     * 二分查找
     *
     * @param n      int整型 数组长度
     * @param target int整型 查找值
     * @param nums   int整型一维数组 有序数组
     * @return int整型
     */
    public int upper_bound_(int n, int target, int[] nums) {
        int left = 0, right = n - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            // 找到等于target值，mid向前移动
            if (nums[mid] == target) {
                while (nums[mid] == target) {
                    mid--;
                }
                return mid + 1 + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // mid到最后，说明没有找到
        if (mid == n - 1) {
            return n + 1;
        } else {
            return mid + 1;
        }
    }
}
