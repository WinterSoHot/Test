package cn.dx;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/4
 */
public class Main9 {

    public TreeNode cut(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        // q保存上一层的节点
        Deque<TreeNode> q = new ArrayDeque<>();
        // 保存当前层的节点
        Deque<TreeNode> p = new ArrayDeque<>();
        p.offer(tree);
        boolean flag = true;
        while (!p.isEmpty() && flag) {
            int sz = p.size();
            // 保存上一层节点
            q.clear();
            q.addAll(p);
            for (int i = 0; i < sz; i++) {
                TreeNode cur = p.poll();
                // 检查当前层的子节点是否存在空节点，空节点就结束
                if (cur.left == null) {
                    flag = false;
                } else {
                    p.offer(cur.left);
                }
                if (cur.right == null) {
                    flag = false;
                } else {
                    p.offer(cur.right);
                }
            }
        }
        if (!flag) {
            for (TreeNode treeNode : q) {
                // 剪除上一层节点
                treeNode.left = null;
                treeNode.right = null;
            }
        }
        return tree;

    }

    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
