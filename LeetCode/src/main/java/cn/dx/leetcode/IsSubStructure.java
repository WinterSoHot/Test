package cn.dx.leetcode;

import java.util.Stack;

/**
 * IsSubStructure TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-6-15 下午2:51
 **/
public class IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(A);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == B.val) {
                if (isSame(node, B)) {
                    return true;
                }
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return false;
    }

    public boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        if (b == null) {
            return false;
        }

        if (a.val == b.val) {
            boolean flag1, flag2;
            flag1 = true;
            flag2 = true;
            if (b.left != null) {
                flag1 = isSame(a.left, b.left);
            }
            if (b.right != null){
                flag2 = isSame(a.right, b.right);
            }
            return flag1 && flag2;
        }
        return false;
    }
}
