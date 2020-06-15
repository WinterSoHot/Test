package cn.dx.leetcode;

/**
 * TreeNode TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-6-15 下午1:35
 **/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
