package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 1530. 好叶子节点对的数量
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * <p>
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * <p>
 * 返回树中 好叶子节点对的数量 。
 * <p>
 * TODO 待优化
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/24
 **/
public class NumberOfGoodLeafPair {
    int ans = 0;

    public int countPairs(TreeNode root, int distance) {
        List<Integer> leafDistance = new LinkedList<>();
        deepFirstSearch(root, leafDistance, 0, distance);
        return ans;
    }

    private void deepFirstSearch(TreeNode node, List<Integer> leafDistance, int curDis, int distance) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leafDistance.add(curDis);
            return;
        }
        List<Integer> leftLeafDistance = new LinkedList<>();
        List<Integer> rightLeafDistance = new LinkedList<>();
        deepFirstSearch(node.left, leftLeafDistance, curDis + 1, distance);
        deepFirstSearch(node.right, rightLeafDistance, curDis + 1, distance);
        for (int i = 0; i < leftLeafDistance.size(); i++) {
            for (int j = 0; j < rightLeafDistance.size(); j++) {
                if (leftLeafDistance.get(i) + rightLeafDistance.get(j) - 2 * curDis <= distance) {
                    ans++;
                }
            }
        }
        leafDistance.addAll(leftLeafDistance);
        leafDistance.addAll(rightLeafDistance);
    }
}
