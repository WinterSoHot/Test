package cn.dx.leetcode;

/**
 * 978. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * <p>
 * 返回 A 的最大湍流子数组的长度。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/8
 **/
public class LongestTurbulentSubArray {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int left = 0, right = 1;
        boolean pre = arr[right] > arr[left];
        int ans = 1;
        while (right < n) {
            if (arr[right] == arr[right - 1]) {
                // 如果当前连续相等
                // 直接移动left和right
                ans = Math.max(ans, right - left);
                left = right;
                right++;
                // 超出范围
                if (right == n) {
                    break;
                }
                pre = arr[right] > arr[left];
                continue;
            }
            boolean cur = arr[right] > arr[right - 1];
            if (pre != cur) {
                // 前后不相同，继续
                pre = cur;
            } else {
                // 前后相同
                ans = Math.max(ans, right - left);
                left = right - 1;
                pre = arr[right] > arr[left];
            }
            right++;
        }
        ans = Math.max(ans, right - left);
        return ans;
    }

    public static void main(String[] args) {
        LongestTurbulentSubArray lts = new LongestTurbulentSubArray();
        System.out.println(lts.maxTurbulenceSize(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
        System.out.println(lts.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println(lts.maxTurbulenceSize(new int[]{4, 8, 12, 16}));
        System.out.println(lts.maxTurbulenceSize(new int[]{100}));
        System.out.println(lts.maxTurbulenceSize(new int[]{9, 9}));
        System.out.println(lts.maxTurbulenceSize(new int[]{1, 2, 3, 1, 1, 1}));
    }
}
