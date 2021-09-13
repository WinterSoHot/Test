package cn.dx.leetcode;

public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        int[] res = new int[m + n];
        int i, j;
        i = 0;
        j = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                res[i + j] = nums1[i];
                i++;
            }
            if (nums1[i] > nums2[j]) {
                res[i + j] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            res[i + j] = nums1[i];
            i++;
        }
        while (j < n) {
            res[i + j] = nums2[j];
            j++;
        }

        for (int x = 0; x < m + n; x++) {
            System.out.print(res[x]);
        }
        for (int k = 0; k < m+n; k++) {
            nums1[k] = res[k];
        }
    }
}
