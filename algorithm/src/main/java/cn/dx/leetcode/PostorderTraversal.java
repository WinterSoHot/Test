package cn.dx.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * <p>
 * 难度：中等
 * <p>
 * 方法：用stack实现二叉树的后序遍历
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/29
 */
public class PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            stack.push(node.left);
            stack.push(node.right);
            res.add(node.val);
        }
        Collections.reverse(res);
        return res;
    }
}
