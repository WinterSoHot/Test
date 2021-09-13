package cn.dx.leetcode.biweekly.c46;

import java.util.Arrays;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/1
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        System.out.println(Arrays.toString(nums));
        for (int i = nums.length - 1; i > nums.length - k; i--) {
            swap(nums, i, 0);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, max = i;
        if (l < heapSize && nums[l] > nums[max]) {
            max = l;
        }
        if (r < heapSize && nums[r] > nums[max]) {
            max = r;
        }
        if (max != i) {
            swap(nums, i, max);
            maxHeapify(nums, max, heapSize);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        FindKthLargest fk = new FindKthLargest();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int res = fk.findKthLargest(nums, 2);
        System.out.println(res);
        System.out.println(Arrays.toString(nums));
    }

}
