package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * IsSymmetric https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 *
 * @author dongxian
 * @version 1.0
 * 20-6-15 下午1:34
 **/
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        List<TreeNode> helpList = new ArrayList<>();
        helpList.add(root.left);
        helpList.add(root.right);
        while (helpList.size() > 0) {
            int halfLen = helpList.size() >> 1;
            for (int i = 0; i < halfLen; i++) {
                TreeNode node = helpList.remove(0);
                if (node != null) {
                    stack.push(node.val);
                    helpList.add(node.left);
                    helpList.add(node.right);
                } else {
                    stack.push(null);
                }
            }
            for (int i = 0; i < halfLen; i++) {
                TreeNode node = helpList.remove(0);
                Integer aval = stack.pop();
                if (node != null) {
                    if (aval == null) {
                        return false;
                    }
                    if (node.val == aval) {
                        helpList.add(node.left);
                        helpList.add(node.right);
                    } else {
                        return false;
                    }
                } else {
                    if (aval != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.right = null;
        root.right.left = new TreeNode(2);

        IsSymmetric is = new IsSymmetric();
        System.out.println(is.isSymmetric(root));
    }
}
