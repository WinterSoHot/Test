package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * <p>
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * <p>
 * 计算监控树的所有节点所需的最小摄像头数量。
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-cameras/
 * <p>
 * 难度：困难
 * <p>
 * 思路：每个节点都只有三种状态： 1：该节点安装了监视器 2：该节点可观，但没有安装监视器 3：该节点不可观
 *
 * https://leetcode-cn.com/problems/binary-tree-cameras/solution/jian-kong-er-cha-shu-by-leetcode-solution/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/22
 */
public class BiTreeCamera {
    int ans = 0;

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int dfs = DFS(root);
        if (dfs == 2) {
            ans++;
        }
        return ans;
    }

    /**
     * 1：该节点安装了监视器 2：该节点可观，但没有安装监视器 3：该节点不可观
     *
     * @param node 节点
     * @return 当前节点的状态
     */
    private int DFS(TreeNode node) {
        if (node == null) {
            return 1;
        }
        // 左右子节点的状态
        int left = DFS(node.left), right = DFS(node.right);
        if (left == 2 || right == 2) {
            // 左右节点不可见，则当前节点要安装摄像头
            ans++;
            return 0;
        } else if (left == 0 || right == 0) {
            // 左右节点有摄像头，则当前节点可见
            return 1;
        } else {
            // 不可见
            return 2;
        }
    }
}
