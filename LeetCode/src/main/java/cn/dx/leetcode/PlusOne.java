package cn.dx.leetcode;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int remain = 1;
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            int digit = digits[i];
            digit += remain;
            remain = digit / 10;
            digits[i] = digit % 10;
        }
        if (remain != 0) {
            int[] res = new int[n + 1];
            System.arraycopy(digits, 0, res, 1, n);
            res[0] = remain;
            return res;
        }
        return digits;
    }
}
