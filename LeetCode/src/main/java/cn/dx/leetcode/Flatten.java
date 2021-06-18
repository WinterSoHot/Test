package cn.dx.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author gudongxian
 * @version 0.1
 * @date 8/2/2020
 */
public class Flatten {

    public void search(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            if (node.left != null) {
                search(node.left,list);
            }
            if (node.right != null) {
                search(node.right,list);
            }
        }
    }

    public void flatten(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            search(root, list);
        }else {
            return;
        }
        System.out.println(list);
        TreeNode curNode = root;
        for (int i = 1; i < list.size(); i++) {
            Integer v = list.get(i);
            curNode.left = null;
            if (curNode.right == null) {
                curNode.right = new TreeNode();
            }
            curNode.right.val = v;
            curNode = curNode.right;
        }
    }
}
