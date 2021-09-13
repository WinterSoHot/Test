package cn.dx.leetcode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * <p>
 * 难度：困难
 * <p>
 * 答案思路：https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/xue-xi-shu-ju-jie-gou-he-suan-fa-de-gao-xiao-fang-fa
 * <p>
 * 后序遍历
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/31
 */
public class MaxPathSum {
    int ans = Integer.MIN_VALUE;

    public int oneSideMax(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左边的大小
        int left = Math.max(0, oneSideMax(node.left));
        // 右边的大小
        int right = Math.max(0, oneSideMax(node.right));
        // 当前答案
        ans = Math.max(ans, left + right + node.val);
        // 计算当前节点的较大的一边路径和
        return Math.max(left, right) + node.val;
    }


    public int maxPathSum(TreeNode root) {
        oneSideMax(root);
        return ans;
    }
}
