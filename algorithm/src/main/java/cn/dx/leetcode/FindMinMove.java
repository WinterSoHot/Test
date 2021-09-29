package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 517. 超级洗衣机
 * https://leetcode-cn.com/problems/super-washing-machines/
 *
 * @author gudongxian
 * @date 2021/9/29
 */
public class FindMinMove {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = Arrays.stream(machines).sum();
        if (sum % n != 0) {
            return -1;
        }

        int t = sum / n;
        int ls = 0, rs = sum;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            rs -= machines[i];
            int a = Math.max(t * i - ls, 0);
            int b = Math.max((n - i - 1) * t - rs, 0);
            ans = Math.max(ans, a + b);
            ls += machines[i];
        }
        return ans;
    }
}
