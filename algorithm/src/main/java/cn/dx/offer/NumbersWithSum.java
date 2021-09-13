package cn.dx.offer;

import java.util.ArrayList;

/**
 * 和为S的两个数字
 * <p>
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class NumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int n = array.length;
        boolean pro = false;
        int first = 0, second = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] + array[j] == sum) {
                    if (!pro) {
                        first = array[i];
                        second = array[j];
                        pro = true;
                    } else {
                        if (array[i] * array[j] < first * second) {
                            first = array[i];
                            second = array[j];
                        }
                    }
                }
            }
        }
        ArrayList<Integer> ret = new ArrayList<>();
        if (!pro) {
            return ret;
        }
        ret.add(first);
        ret.add(second);
        return ret;
    }
}
