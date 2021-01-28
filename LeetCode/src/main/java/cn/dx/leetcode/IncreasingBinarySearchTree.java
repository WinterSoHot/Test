package cn.dx.leetcode;

/**
 * 897. 递增顺序查找树
 * <p>
 * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
 * <p>
 * level: easy
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/24
 **/
public class IncreasingBinarySearchTree {
    TreeNode copyAns = null;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans = new TreeNode();
        copyAns = ans;
        inOrder(root);
        return ans.right;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        copyAns.right = new TreeNode(node.val);
        copyAns = copyAns.right;
        inOrder(node.right);
    }
}
