package cn.dx.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组中只出现一次的两个数字
 * <p>
 * 题目描述
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class NumAppearOnce {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] FindNumsAppearOnce(int[] array) {
        int n = array.length;
        if (n == 2) {
            return array;
        }
        Arrays.sort(array);
        List<Integer> ans = new ArrayList<>(2);
        for (int i = 0; i < n - 1; ) {
            if (array[i] == array[i + 1]) {
                i += 2;
            } else {
                ans.add(array[i]);
                i += 1;
            }
        }
        if (ans.size() == 1) {
            ans.add(array[n - 1]);
        }
        int[] ret = new int[2];
        ret[0] = ans.get(0);
        ret[1] = ans.get(1);
        return ret;
    }
}
