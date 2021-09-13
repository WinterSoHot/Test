package cn.dx.offer;

import cn.dx.bullcode.TreeNode;

/**
 * 树的子结构
 * <p>
 * 题目描述
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/7
 */
public class HasSubStructure {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        return (root1 != null && root2 != null) && (isSame(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2));
    }

    private boolean isSame(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }
}
