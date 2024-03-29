package cn.dx.leetcode.tree;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/8
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
