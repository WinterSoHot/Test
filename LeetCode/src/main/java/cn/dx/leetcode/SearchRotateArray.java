package cn.dx.leetcode;

/**
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
 * <p>
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvyz1t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/15
 **/
public class SearchRotateArray {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRotateArray sra = new SearchRotateArray();
        System.out.println(sra.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(sra.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(sra.search(new int[]{1}, 0));
        System.out.println(sra.search(new int[]{1, 3}, 0));
        System.out.println(sra.search(new int[]{3, 5, 1}, 3));
        System.out.println(sra.search(new int[]{5, 1, 3}, 5));
    }
}
