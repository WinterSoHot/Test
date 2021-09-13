package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/23
 */
public class MergeTwoTree {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            if (t1 != null) {
                return t1;
            }
            return t2;
        }

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.offer(t1);
        q2.offer(t2);

        while (!q1.isEmpty() || !q2.isEmpty()) {
            int sz1 = q1.size();
            int sz2 = q2.size();

            int sz = Math.max(sz1, sz2);
            for (int i = 0; i < sz; i++) {
                TreeNode node1 = q1.poll();
                TreeNode node2 = q2.poll();
                node1.val += node2.val;

                if (node1.left != null || node2.left != null) {
                    if (node1.left == null) {
                        node1.left = new TreeNode(0);
                    }
                    if (node2.left == null) {
                        node2.left = new TreeNode(0);
                    }
                    q1.offer(node1.left);
                    q2.offer(node2.left);
                }
                if (node1.right != null || node2.right != null) {
                    if (node1.right == null) {
                        node1.right = new TreeNode(0);
                    }
                    if (node2.right == null) {
                        node2.right = new TreeNode(0);
                    }
                    q1.offer(node1.right);
                    q2.offer(node2.right);
                }
            }
        }
        return t1;
    }
}
