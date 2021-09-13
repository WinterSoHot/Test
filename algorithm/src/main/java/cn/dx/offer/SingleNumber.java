package cn.dx.offer;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * <p>
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * 作者：Krahets
 * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/9hyq1r/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/4
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        // 求出每一位二进位为1的数目
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % 3;
        }
        return res;
    }
}
