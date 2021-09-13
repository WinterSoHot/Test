package cn.dx.leetcode;

/**
 * 1319. 连通网络的操作次数
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
 * <p>
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * <p>
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/23
 **/
public class NumberOfOperation {
    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        int over = connections.length;
        for (int[] connection : connections) {
            if (uf.union(connection[0], connection[1])) {
                over--;
            }
        }
        if (uf.getCount() - over > 1) {
            return -1;
        }
        return uf.getCount() - 1;
    }
}

class UnionFind {
    /**
     * 当前i的父节点
     */
    private int[] parent;
    /**
     * 当前i的子树的高度
     */
    private int[] rank;
    private int count;

    /**
     * 构造方法
     *
     * @param N 节点个数
     */
    public UnionFind(int N) {
        this.count = N;
        this.parent = new int[N];
        this.rank = new int[N];
        for (int i = 0; i < N; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 找到当前节点的根节点
     *
     * @param x 节点x
     * @return 根节点编号
     */
    public int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    /**
     * 将 p 和 q 归并到相同的分量中
     *
     * @param p 节点p
     * @param q 节点q
     */
    public boolean union(int p, int q) {
        //
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return false;
        }
        if (rank[rootP] == rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ]++;
        } else if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
        }
        count--;
        return true;
    }
}
