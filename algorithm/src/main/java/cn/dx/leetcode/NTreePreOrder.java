package cn.dx.leetcode;

import java.util.*;

import cn.dx.leetcode.tree.Node;

/**
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 难度：简单
 *
 * 方法：递归，迭代
 *
 * 本题用迭代进行求解
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/8
 */
public class NTreePreOrder {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root==null){
            return res;
        }
        Stack<Node> q = new Stack<>();
        q.push(root);
        while (!q.isEmpty()){
            Node node = q.pop();
            res.add(node.val);
            Collections.reverse(node.children);
            for (Node child : node.children) {
                q.add(child);
            }
        }
        return res;
    }
}
