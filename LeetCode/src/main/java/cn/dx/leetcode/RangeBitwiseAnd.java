package cn.dx.leetcode;

/**
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/23
 */
public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        // 找 m-n 范围内，前缀相等的部分
        for (;m!=n; offset++) {
            m >>= 1;
            n >>= 1;
        }
        // m=5,n=7。  m=n=01
        // 0100
        return n << offset;
    }

    public static void main(String[] args) {
        RangeBitwiseAnd rba = new RangeBitwiseAnd();
        System.out.println(rba.rangeBitwiseAnd(5,7));
        System.out.println(Integer.toBinaryString(7 << 2));
    }
}
