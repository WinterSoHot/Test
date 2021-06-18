package cn.dx.offer;

import cn.dx.leetcode.TreeNode;

/**
 * 二叉搜索树与双向链表
 * <p>
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/10
 */
public class ConvertTreeToList {
    TreeNode pre = null;
    TreeNode head = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        return head;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        if (head == null) {
            head = node;
        }
        pre = node;
        inOrder(node.right);

    }
}
