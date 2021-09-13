package cn.dx.leetcode;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/29
 */
public class SumRootToLeafNumbers {
    int res = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return res;
        }
        DFS(root, 0);
        return res;
    }

    private void DFS(TreeNode node, int cur) {
        cur = cur * 10 + node.val;
        if (node.left == null && node.right == null) {
            res += cur;
            return;
        }
        if (node.left != null) {
            DFS(node.left, cur);
        }
        if (node.right != null) {
            DFS(node.right, cur);
        }
    }
}
