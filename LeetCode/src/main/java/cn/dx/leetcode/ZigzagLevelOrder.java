package cn.dx.leetcode;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> item = new LinkedList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                item.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (!flag) {
                Collections.reverse(item);
            }
            flag = !flag;
            res.add(item);
        }
        return res;
    }
}
