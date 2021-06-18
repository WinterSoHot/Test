package cn.dx.offer;

import cn.dx.leetcode.TreeNode;

/**
 * 平衡二叉树
 * <p>
 * 题目描述
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class BalancedTree {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 根据计算子树高度
        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);
        // 对于当前节点是否平衡
        boolean isBalance = Math.abs(leftHeight - rightHeight) <= 1;
        // 除了当前节点满足，也需要子树都平衡
        return isBalance && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public int treeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(treeHeight(node.left), treeHeight(node.right)) + 1;
    }
}
