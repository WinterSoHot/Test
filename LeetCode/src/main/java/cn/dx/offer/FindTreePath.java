package cn.dx.offer;

import cn.dx.bullcode.TreeNode;

import java.util.ArrayList;

/**
 * 二叉树中和为某一值的路径
 * <p>
 * 题目描述
 * <p>
 * 输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/10
 */
public class FindTreePath {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        dfs(root, target);
        return ans;
    }

    private void dfs(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        if (target - node.val == 0 && node.left == null && node.right == null) {
            path.add(node.val);
            ans.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        path.add(node.val);
        dfs(node.left, target - node.val);
        path.remove(path.size() - 1);

        path.add(node.val);
        dfs(node.right, target - node.val);
        path.remove(path.size() - 1);
    }
}
