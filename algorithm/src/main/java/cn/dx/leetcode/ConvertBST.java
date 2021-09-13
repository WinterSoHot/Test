package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/21
 */
public class ConvertBST {
    List<Integer> nodeVal = new LinkedList<>();
    List<Integer> preSum = new LinkedList<>();
    int ns = 0;

    /**
     * 当前遍历得累计和
     */
    int sum = 0;

    /**
     * 反序中序遍历
     * <p>
     * 说明：二叉搜索树得中序遍历时递增得序列，因此，反向中序遍历是一个递减序列
     *
     * @param root 节点
     * @return 返回节点
     */
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }


    public TreeNode convertBST2(TreeNode root) {
        inOrder(root);
        ns = nodeVal.size();
        System.out.println(ns);
        updateNodeVal(root);
        return root;
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        nodeVal.add(node.val);
        if (preSum.size() == 0) {
            preSum.add(node.val);
        } else {
            preSum.add(preSum.get(preSum.size() - 1) + node.val);
        }
        inOrder(node.right);
    }

    public void updateNodeVal(TreeNode node) {
        if (node == null) {
            return;
        }
        int cur = nodeVal.indexOf(node.val);
        node.val = preSum.get(ns - 1) - preSum.get(cur);
        updateNodeVal(node.left);
        updateNodeVal(node.right);
    }

    public static void printTree(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "\t");
        printTree(node.left);
        printTree(node.right);
    }

    public static void main(String[] args) {
        ConvertBST cv = new ConvertBST();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(1);
        cv.convertBST(root);
        printTree(root);
    }
}
