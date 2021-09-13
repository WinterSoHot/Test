package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/12
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
