package cn.dx.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/17
 */
public class BalanceBinaryTree {
    Map<TreeNode,Integer> nodeHeight = new HashMap<>();

    public int calTreeHeight(TreeNode node){
        if (node==null){
            return 0;
        }
        if (nodeHeight.containsKey(node)){
            return nodeHeight.getOrDefault(node,0);
        }
        int leftHeight = calTreeHeight(node.left);
        int rightHeight = calTreeHeight(node.right);
        if (!nodeHeight.containsKey(node.left)){
            nodeHeight.put(node.left,leftHeight);
        }
        if (!nodeHeight.containsKey(node.right)){
            nodeHeight.put(node.right,rightHeight);
        }
        return Math.max(leftHeight + 1, rightHeight + 1);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        return Math.abs(calTreeHeight(root.left)-calTreeHeight(root.right)) <=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public static void main(String[] args) {
        BalanceBinaryTree bbt = new BalanceBinaryTree();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode();
        node.right = new TreeNode();
        node.left.right = new TreeNode();
        System.out.println(bbt.calTreeHeight(node));;
    }
}
