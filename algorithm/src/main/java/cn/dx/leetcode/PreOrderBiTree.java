package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * <p>
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/27
 */
public class PreOrderBiTree {
    List<Integer> ret = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        // 递归解法
        preOrder(root);

        // 迭代解法
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            ret.add(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }
        return ret;
    }

    /**
     * 递归前序遍历
     *
     * @param node
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        ret.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }
}
