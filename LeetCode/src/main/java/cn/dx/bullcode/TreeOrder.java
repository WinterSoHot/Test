package cn.dx.bullcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/4
 */
public class TreeOrder {
    /**
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders(TreeNode root) {
        // write code here
        preOrder(root);
        int[][] res = new int[3][list.size()];
        res[0] = list.stream().mapToInt(value -> value).toArray();
        list.clear();
        inOrder(root);
        res[1] = list.stream().mapToInt(value -> value).toArray();
        list.clear();
        postOrder(root);
        res[2] = list.stream().mapToInt(value -> value).toArray();
        return res;
    }

    List<Integer> list = new ArrayList<>();

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        list.add(node.val);
        inOrder(node.right);
    }

    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        list.add(node.val);
    }
}
