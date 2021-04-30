package cn.dx.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/30
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        Set<Integer> hasSee = new HashSet<>();
        Set<Integer> singeSee = new HashSet<>();
        for (int item : nums) {
            if (!hasSee.contains(item)) {
                if (!singeSee.contains(item)) {
                    singeSee.add(item);
                } else {
                    singeSee.remove(item);
                    hasSee.add(item);
                }
            }
        }
        return singeSee.stream().findFirst().get();
    }

    public int singleNumber2(int[] nums) {
        // 依次确定每个二进制位
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * https://zhuanlan.zhihu.com/p/83106691
     * 最小项表达式
     * <p>
     * https://leetcode-cn.com/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        // 数字电路模拟
        int a = 0;
        int b = 0;
        for (int num : nums) {
            int aNext = (~a & b & num) | (a & ~b & ~num);
            int bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }
}
