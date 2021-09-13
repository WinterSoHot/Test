package cn.dx.leetcode.biweekly.c35;

import java.util.Arrays;
import java.util.function.IntPredicate;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/19
 */
public class SumOddLengthSubarrays {
    public int sumOddLengthSubarrays(int[] arr) {
        int len = arr.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j+=2) {
                res+=sumOfSpan(arr,i,j);
            }
        }
        return res;
    }

    private int sumOfSpan(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end ; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
