package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 难度：简单
 *
 * 方法：递归
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/14
 */
public class InordTraversal {
    private List<Integer> ans = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return ans;
    }
    public void inorder(TreeNode node){
        if (node == null){
            return;
        }
        inorder(node.left);
        ans.add(node.val);
        inorder(node.right);
    }
}
