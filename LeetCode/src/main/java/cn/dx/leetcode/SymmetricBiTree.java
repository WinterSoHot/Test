package cn.dx.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class SymmetricBiTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> q = new LinkedList<>();
        Deque<TreeNode> tmp = new LinkedList<>();

        q.offer(root.left);
        q.offer(root.right);

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz / 2; i++) {
                TreeNode first = q.pollFirst();
                TreeNode last = q.pollLast();
                if (first == null && last == null) {
                    continue;
                } else if (first == null) {
                    return false;
                } else if (last == null) {
                    return false;
                } else if (first.val != last.val) {
                    return false;
                }
                tmp.offerFirst(first.right);
                tmp.offerFirst(first.left);
                tmp.offerLast(last.left);
                tmp.offerLast(last.right);
            }
            q.addAll(tmp);
            tmp.clear();
        }
        return true;
    }
}
