package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * <p>
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * <p>
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * <p>
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/8
 **/
public class SplitIntoFibonacci {
    /**
     * 回溯 + 剪枝
     *
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new LinkedList<>();
        backtrack(res, S, S.length(), 0, 0, 0);
        return res;
    }

    /**
     * 回溯
     *
     * @param res  结果集
     * @param s    字符串
     * @param n    字符串长度
     * @param i    当前开始分割位置
     * @param sum  前面两个数的和
     * @param prev 前面一个数
     * @return
     */
    private boolean backtrack(List<Integer> res, String s, int n, int i, int sum, int prev) {
        if (i == n) {
            // 完全分割完
            return res.size() >= 3;
        }
        long curLong = 0;
        for (int x = i; x < n; x++) {
            // 开头为 0 结束
            if (x > i && s.charAt(i) == '0') {
                break;
            }
            // 计算当前位置的值
            curLong = curLong * 10 + s.charAt(x) - '0';
            if (curLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) curLong;
            if (res.size() >= 2) {
                // 不满足等于前面两个值
                // 小于则继续往后分割，大于则中断
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;
                }
            }
            // 满足条件后
            res.add(curr);
            // 从当前位置继续向后寻找，后面满足则继续，否则回溯
            if (backtrack(res, s, n, x + 1, prev + curr, curr)) {
                return true;
            } else {
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}
