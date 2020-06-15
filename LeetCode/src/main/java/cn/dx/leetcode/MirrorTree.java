package cn.dx.leetcode;

/**
 * MirrorTree TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-6-15 下午2:14
 **/
public class MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null)
            return null;
        mirrorLF(root);
        return root;
    }

    private void mirrorLF(TreeNode lp) {
        if (lp.left != null && lp.right != null) {
            TreeNode node = lp.left;
            lp.left = lp.right;
            lp.right = node;
            mirrorLF(lp.left);
            mirrorLF(lp.right);
        } else if (lp.left == null && lp.right != null) {
            lp.left = lp.right;
            lp.right = null;
            mirrorLF(lp.left);
        } else if (lp.left != null) {
            lp.right = lp.left;
            lp.left = null;
            mirrorLF(lp.right);
        }

    }
}
