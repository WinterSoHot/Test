package cn.dx.offer;

/**
 * 题目描述
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/4
 */
public class ReOrderArray {
    public int[] reOrderArray1(int[] array) {
        // 使用插入排序
        int len = array.length;
        if (array == null || len == 0) {
            return array;
        }
        // 当前奇数插入的位置
        int i = 0;
        for (int s = 0; s < len; s++) {
            int cur = array[s];
            if (!isEven(cur)) {
                int k = s;
                while (k > i) {
                    array[k] = array[k - 1];
                    k--;
                }
                array[i] = cur;
                i++;
            }
        }
        return array;
    }

    public int[] reOrderArray(int[] array) {
        int oddCount = 0;
        for (int i : array) {
            if (!isEven(i)) {
                oddCount++;
            }
        }
        int i = 0, j = oddCount;
        int[] copy = array.clone();
        for (int v : array) {
            if (!isEven(v)) {
                copy[i++] = v;
            } else {
                copy[j++] = v;
            }
        }
        return copy;
    }

    public boolean isEven(int v) {
        return (v & 1) == 0;
    }
}
