package cn.dx.leetcode;

/**
 *
 * 26. 翻转二叉树
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * 难度：简单
 *
 * 方法：递归
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/16
 */
public class InvertBiTree {
    public TreeNode invertTree(TreeNode root) {
        exchange(root);
        return root;
    }

    public void exchange(TreeNode node){
        if (node == null){
            return;
        }
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        exchange(node.left);
        exchange(node.right);
    }
}
