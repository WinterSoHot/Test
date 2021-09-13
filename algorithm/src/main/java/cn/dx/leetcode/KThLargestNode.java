package cn.dx.leetcode;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/24
 **/
public class KThLargestNode {
    Integer K = null;
    int ans = 0;

    public int kthLargest(TreeNode root, int k) {
        K = k;
        inOrder(root);
        return ans;
    }

    private void inOrder(TreeNode node) {
        if (node == null || K == 0) {
            return;
        }
        inOrder(node.right);
        K--;
        if (K == 0) {
            ans = node.val;
        }
        inOrder(node.left);
    }
}
