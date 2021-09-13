package cn.dx.leetcode;

public class QuickUnionFind {

    private int[] id;
    private int[] sz;


    public QuickUnionFind(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public void print() {
        System.out.println("当前数组:");
        for (int i = 0; i < id.length; i++) {
            System.out.printf("%d\t", id[i]);
        }
        System.out.println();
        for (int i = 0; i < sz.length; i++) {
            System.out.printf("%d\t", sz[i]);
        }
    }

    public static void main(String[] args) {
        QuickUnionFind quickUnionFind = new QuickUnionFind(10);
        quickUnionFind.union(1,2);
        quickUnionFind.union(2,3);
        System.out.println(quickUnionFind.connected(1,4));
        System.out.println(quickUnionFind.connected(2,3));
        quickUnionFind.print();

    }
}
