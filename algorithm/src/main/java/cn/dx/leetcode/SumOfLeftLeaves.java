package cn.dx.leetcode;

/**
 * 计算给定二叉树的所有左叶子之和。
 * <p>
 * https://leetcode-cn.com/problems/sum-of-left-leaves/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/19
 */
public class SumOfLeftLeaves {
    int res = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return res;
        }
        Dfs(root, 1);
        return res;
    }

    private void Dfs(TreeNode node, int flag) {
        if (node.left == null && node.right == null && flag == 0) {
            res += node.val;
            return;
        }
        if (node.left != null) {
            Dfs(node.left, 0);
        }
        if (node.right != null) {
            Dfs(node.right, 1);
        }
    }

    public static void main(String[] args) {
        SumOfLeftLeaves sll = new SumOfLeftLeaves();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(sll.sumOfLeftLeaves(root));
    }
}
