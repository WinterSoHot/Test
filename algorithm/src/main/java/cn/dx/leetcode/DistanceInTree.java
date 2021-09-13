package cn.dx.leetcode;

/**
 * 练习：二叉树上两点的距离
 * 给定一棵二叉树。现在给定 p 和 q，求 p 到 q 的最短距离。
 * 注意：每个节点值均不同。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/22
 */
public class DistanceInTree {
    public int distanceInTree(TreeNode root, int p, int q) {
        TreeNode ancestor = findAncestor(root, p, q);
        return getDistance(ancestor, p) + getDistance(ancestor, q);
    }

    /**
     * 找到两节点得最近祖先
     */
    public TreeNode findAncestor(TreeNode root, int p, int q) {
        // 结束条件，找到节点
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = findAncestor(root.left, p, q);
        TreeNode right = findAncestor(root.right, p, q);
        // 在左右子树找到符合条件的节点，则当前节点作为最近根
        if (left != null && right != null) {
            return root;
        }
        // 如果一个树上找不到，则返回找到的树的根节点
        return left == null ? right : left;
    }

    /**
     * 计算祖先节点和当前节点之间距离
     */
    public int getDistance(TreeNode ancestor, int value) {
        if (ancestor == null) {
            return Integer.MIN_VALUE;
        }
        if (ancestor.val == value) {
            return 0;
        }
        return Math.max(getDistance(ancestor.left, value) + 1, getDistance(ancestor.right, value) + 1);
    }
}
