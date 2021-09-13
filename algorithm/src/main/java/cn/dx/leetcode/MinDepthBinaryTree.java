package cn.dx.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/21
 */
public class MinDepthBinaryTree {
    public int minDepth(TreeNode root) {
        // 特殊情况
        if (root == null){
            return 0;
        }
        // 广度优先搜索，至少为1
        int depth = 1;
        Deque<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()){
            // Tip: 首先保存当前需要出队的个数，不能直接在for中写，因为循环语句会入队
            int csize = nodes.size();
            for (int i = 0; i < csize; i++) {
                TreeNode curNode = nodes.poll();
                // 当前节点没有子节点，达到最小深度
                if (curNode.left==null && curNode.right == null){
                    return depth;
                }
                if (curNode.left!=null){
                    nodes.offer(curNode.left);
                }
                if (curNode.right!=null){
                    nodes.offer(curNode.right);
                }
            }
            // Tip: 每一层遍历完，则深度+1
            depth++;
        }
        return depth;
    }
}
