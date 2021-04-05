package cn.dx.offer;

import cn.dx.leetcode.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 * <p>
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 B：
 * <p>
 * 4
 * /
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/3
 */
public class IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // A 或 B 为  null的话直接返回
        // 否则判断当前节点是否包含子结构
        // 当前节点不包含则 判断子节点是否包含
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean recur(TreeNode a, TreeNode b) {
        // b 为 null 表示 b已经超过叶子节点
        if (b == null) {
            return true;
        }
        // a 为 null表示a超过叶子节点
        // 当前节点不相等
        if (a == null || a.val != b.val) {
            return false;
        }
        // 判断左右节点是否相等
        return recur(a.left, b.left) && recur(a.right, b.right);
    }
}
