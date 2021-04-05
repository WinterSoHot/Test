package cn.dx.offer;

/**
 * 数组中的逆序对
 * <p>
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，
 * 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class InversePairs {
    int cnt = 0;

    public int InversePairs(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return cnt;
    }

    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int k = 0, i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
                //如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                cnt = (cnt + (mid - i + 1)) % 1000000007;
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= end) {
            tmp[k++] = array[j++];
        }
        for (int s = start; s <= end; s++) {
            array[s] = tmp[s - start];
        }
    }
}
