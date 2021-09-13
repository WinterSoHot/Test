package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 * <p>
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/14
 **/
public class PrefixDivBy5 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> ans = new LinkedList<>();
        int cur = 0;
        for (int i : A) {
            cur = (cur << 2 + i) % 5;
            ans.add(cur == 0);
        }
        return ans;
    }
}
