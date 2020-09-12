package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.ToDoubleFunction;

/**
 *
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 * 难度：简单
 *
 * 方法：BFS
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/12
 */
public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()){
            int sz = q.size();
            double v = q.stream().mapToDouble(value -> value.val).average().getAsDouble();
            ans.add(v);
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null){
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }
}
