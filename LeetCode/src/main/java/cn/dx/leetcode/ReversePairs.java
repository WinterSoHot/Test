package cn.dx.leetcode;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        // 临时数组，用于保存排序前的数组
        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    /**
     * nums[left...right] 计算逆序对的个数
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);
        if (nums[mid] < nums[mid + 1]) {
            // 整个左右有序
            return leftPairs + rightPairs;
        }
        // 计算逆序
        int crossPair = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + crossPair + rightPairs;
    }

    /**
     * nums[left...mid] 有序地nums[mid+1...right] 有序
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
    }
}
