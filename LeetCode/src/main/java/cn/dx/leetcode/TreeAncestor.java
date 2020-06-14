package cn.dx.leetcode;

/**
 * TreeAncestor https://leetcode-cn.com/contest/weekly-contest-193/problems/kth-ancestor-of-a-tree-node/
 *
 * @author dongxian
 * @version 1.0
 * 20-6-14 上午11:28
 **/
public class TreeAncestor {
    private int[] parent;
    private int n;

    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        this.parent = parent;
    }

    public int getKthAncestor(int node, int k) {
        while (k > 0) {
            if (node == -1) {
                return -1;
            }
            node = parent[node];
            k--;
        }
        return parent[node];
    }

    public static void main(String[] args) {
        System.out.println(6 >> 2);
    }
}
