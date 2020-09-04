package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 难度：简单
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/4
 */
public class BinaryTreePaths {
    List<String> ans = new ArrayList<>();
    List<Integer> tracks = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return ans;
        }
        tracks.add(root.val);
        DFS(root, tracks);
        return ans;
    }

    private void DFS(TreeNode node, List<Integer> tracks) {
        if (node.left == null && node.right == null) {
            // 添加路径到答案
            StringBuilder sb = new StringBuilder();
            sb.append(tracks.get(0));
            for (int i = 1; i < tracks.size(); i++) {
                sb.append("->").append(tracks.get(i));
            }
            ans.add(sb.toString());
            return;
        }
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        if (leftNode != null) {
            tracks.add(leftNode.val);
            DFS(leftNode, tracks);
            tracks.remove(tracks.size() - 1);
        }
        if (rightNode != null) {
            tracks.add(rightNode.val);
            DFS(rightNode, tracks);
            tracks.remove(tracks.size() - 1);
        }
    }
}
