package cn.dx.offer;

import java.util.Arrays;

/**
 * 数组中出现次数超过一半的数字
 * <p>
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/10
 */
public class MoreThanHalfNum {
    public int MoreThanHalfNum_Solution(int[] array) {
        Arrays.sort(array);
        int num = array[array.length / 2];
        int count = 0;
        for (int i : array) {
            if (i == num) {
                count++;
            }
        }
        return 2 * count > array.length ? num : 0;
    }
}
