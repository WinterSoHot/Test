package cn.dx.leetcode;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/3
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                // i 是 2的幂次
                highBit = i;
            }
            ans[i] = ans[i - highBit] + 1;
        }

//        for (int i = 0; i <= num; i++) {
//            int cur = i;
//            int count = 0;
//            while (cur != 0) {
//                if ((cur & 1) == 1) {
//                    count++;
//                }
//                cur >>= 1;
//            }
//            ans[i] = count;
//        }
        return ans;
    }
}
