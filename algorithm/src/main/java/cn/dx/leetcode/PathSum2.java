package cn.dx.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/26
 */
public class PathSum2 {
    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        deepSearch(root, sum);
        return ans;
    }

    private void deepSearch(TreeNode node, int target) {
        // End
        if (node == null) {
            return;
        }

        // 当前节点和target相等，且节点为叶子节点
        if (target == node.val && node.left == null && node.right == null) {
            path.add(node.val);
            ans.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        // 选择
        path.add(node.val);

        // 递归
        deepSearch(node.left, target - node.val);
        deepSearch(node.right, target - node.val);

        // 撤销
        path.removeLast();
    }
}
