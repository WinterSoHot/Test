package cn.dx.leetcode;

/**
 *
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <br/>
 * "123"<br/>
 * "132"<br/>
 * "213"<br/>
 * "231"<br/>
 * "312"<br/>
 * "321"<br/>
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 难度：中等
 *
 * 思路：回溯 + 剪枝
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/5
 */
public class PermutationSequence {
    StringBuilder sb = new StringBuilder();
    int step = 0;
    String ans;

    /**
     * 阶乘数组
     */
    private int[] factorial;


    /**
     * 计算阶乘数组
     *
     * @param n
     */
    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }


    public String getPermutation(int n, int k) {
        calculateFactorial(n);
        backtrack(n, k);
        return ans;
    }

    public void backtrack(int n, int k) {
        // 结束条件
        if (sb.length() == n) {
            step++;
            if (step == k) {
                ans = sb.toString();
            }
            return;
        }
        // 到达第k个答案就结束
        if (step == k) {
            return;
        }

        // 遍历选中列表
        for (int i = 1; i <= n; i++) {
            if (sb.indexOf(i + "") != -1) {
                continue;
            }
            // 剪枝：计算当前分支存在的叶子节点数目，如果当前分支叶子节点数目大于等于k，表示当前分支包含答案
            // 否则直接跳过当前分支。
            // 当前分支的叶子节点 == 剩下未选的位置的阶乘
            if (step + factorial[n-sb.length()] < k){
                step += factorial[n-sb.length()];
                return;
            }
            //选择
            sb.append(i);
            backtrack(n, k);
            //撤销
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        PermutationSequence ps = new PermutationSequence();
        System.out.println(ps.getPermutation(9, 278893));
    }
}
