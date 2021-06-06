package cn.dx.leetcode;

/**
 * 664. 奇怪的打印机
 * 有台奇怪的打印机有以下两个特殊要求：
 * <p>
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * <p>
 * level: hard
 * <p>
 * 题解：https://leetcode-cn.com/problems/strange-printer/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-xqeo9/
 * <p>
 * 推论：<b>连续相同的一段字符，必然可以归到同一次打印中，而不会让打印次数变多</>
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/26
 */
public class StrangePrinter {
    /**
     * 动态规划
     * 定义：f[l][r]为[l,r]这一段打印成目标结果所需要的最小打印次数
     * 不失一般性考虑 f[l][r]f[l][r] 该如何转移：
     * <p>
     * 只打印 l 这个位置，此时 f[l][r] = f[l + 1][r] + 1f[l][r]=f[l+1][r]+1
     * 不只打印 l 这个位置，而是从 l 打印到 k（需要确保首位相同 s[l] = s[k]s[l]=s[k]）：f[l][r] = f[l][k - 1] + f[k + 1][r], l < k <= rf[l][r]=f[l][k−1]+f[k+1][r],l<k<=r
     *
     * @param s 打印字符串
     * @return 最小打印次数
     */
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f = new int[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                // 只打印l
                f[l][r] = f[l + 1][r] + 1;
                for (int k = l + 1; k <= r; k++) {
                    // 打印 [l,k]
                    if (s.charAt(l) == s.charAt(k)) {
                        f[l][r] = Math.min(f[l][r], f[l][k - 1] + f[k + 1][r]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
}
