package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * <p>
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * <p>
 * 请你返回排序后的数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/6
 */
public class SortByBits {
    /**
     * 计算整数的二进制位为1的个数
     *
     * @param i 输入整数
     * @return 1的个数
     */
    public int getBitOneCount(int i) {
        /*int ret = 0;
        while (i != 0) {
            ret += i % 2;
            i = i / 2;
        }
        return ret;*/

        /*int ret = 0;
        while (i != 0) {
            ret += i & 1;
            i = i >> 1;
        }*/

        String bitStr = Integer.toBinaryString(i);
        return (int) bitStr.chars().filter(value -> value == '1').count();
    }

    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed().sorted((o1, o2) -> {
            // 计算bit为1的数目差值
            int bitDis = getBitOneCount(o1) - getBitOneCount(o2);
            if (bitDis == 0) {
                // 如果差值为0，则比较原本的数值
                return o1 - o2;
            }
            return bitDis;
        }).mapToInt(Integer::intValue).toArray();

    }
}
