package cn.dx.leetcode;

import java.util.Deque;
import java.util.Stack;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/7
 */
public class RecoverTree {

    private TreeNode t1, t2, pre;

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (pre != null && pre.val > node.val) {
            if (t1 == null) {
                t1 = pre;
            }
            t2 = node;
        }
        pre = node;
        inOrder(node.right);
    }

    public void recoverTree(TreeNode root) {
        inOrder(root);
        int temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;
    }
}
