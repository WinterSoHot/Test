package cn.dx.leetcode;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * <p>
 * 难度：简单
 * <p>
 * 方法：利用BST的特征，中序遍历，遍历的过程中，将遍历的前一个值和当前值求绝对值
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/12
 */
public class MinimumDifferenceInBST {
    int res = Integer.MAX_VALUE;
    int preValue = -1;

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return res;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        if (preValue == -1) {
            preValue = node.val;
        } else {
            res = Math.min(res, Math.abs(node.val - preValue));
            preValue = node.val;
        }
        inOrder(node.right);
    }
}
