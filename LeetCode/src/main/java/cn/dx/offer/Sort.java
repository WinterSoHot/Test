package cn.dx.offer;

import java.util.Arrays;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/4
 */
public class Sort {
    public static int[] BubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    public static int[] SelectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i; j < len; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            swap(array, min, i);
        }
        return array;
    }

    public static int[] InsertionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int len = array.length;

        for (int i = 0; i < len - 1; i++) {
            int current = array[i + 1];
            int pre = i;
            while (pre >= 0 && array[pre] > current) {
                array[pre + 1] = array[pre];
                pre--;
            }
            array[pre + 1] = current;
        }
        return array;
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        // 如果区域内的数字少于 2 个，退出递归
        if (start >= end) {
            return;
        }
        // 将数组分区，并获得中间值的下标
        int middle = partition(arr, start, end);
        // 对左边区域快速排序
        quickSort(arr, start, middle - 1);
        // 对右边区域快速排序
        quickSort(arr, middle + 1, end);
    }

    /**
     * 将 arr 从 start 到 end 分区，左边区域比基数小，右边区域比基数大，然后返回中间值的下标
     */
    public static int partition(int[] arr, int start, int end) {
        // 取第一个数为基数
        int pivot = arr[start];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        while (left < right) {
            // 找到第一个大于基数的位置
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 找到第一个小于基数的位置
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            // 交换这两个数，使得左边分区都小于或等于基数，右边分区大于或等于基数
            if (left < right) {
                exchange(arr, left, right);
                left++;
                right--;
            }
        }
        // 如果 left 和 right 相等，单独比较 arr[right] 和 pivot
        if (left == right && arr[right] > pivot) {
            right--;
        }
        // 将基数和轴交换
        exchange(arr, start, right);
        return right;
    }

    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 2, 6, 2, 3, 4, 7};
//        System.out.println(Arrays.toString(Sort.BubbleSort(array)));
//        System.out.println(Arrays.toString(Sort.SelectionSort(array)));
//        System.out.println(Arrays.toString(Sort.InsertionSort(array)));
        Sort.quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
