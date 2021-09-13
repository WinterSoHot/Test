package cn.dx.offer;

/**
 * 数字在升序数组中出现的次数
 * <p>
 * 题目描述
 * 统计一个数字在升序数组中出现的次数。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class NumberOfKInArray {
    public int GetNumberOfK(int[] array, int k) {
        int n = array.length;
        if (n == 0) {
            return 0;
        }
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int ret = 0;
        for (int i = left; i < n && array[i] == k; i++) {
            ret++;
        }
        return ret;
    }

    public static void main(String[] args) {
        NumberOfKInArray gn = new NumberOfKInArray();
        System.out.println(gn.GetNumberOfK(new int[]{1, 2, 3, 3, 3, 3, 4, 5}, 3));
    }
}
