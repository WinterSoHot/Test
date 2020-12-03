package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            // 不需要移动
            return;
        }
        int[] tmp = new int[k];
        System.arraycopy(nums, 0, tmp, 0, k);
        for (int i = k; i < n + k; i += k) {
            for (int j = 0; j < k; j++) {
                int tv = nums[(i + j) % n];
                nums[(i + j) % n] = tmp[j];
                tmp[j] = tv;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        RotateArray ra = new RotateArray();
        ra.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
