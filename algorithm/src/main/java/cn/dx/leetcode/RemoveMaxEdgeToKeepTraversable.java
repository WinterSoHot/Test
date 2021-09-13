package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 1579. 保证图可完全遍历
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
 * <p>
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * <p>
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/27
 **/
public class RemoveMaxEdgeToKeepTraversable {
    public static void main(String[] args) {
        RemoveMaxEdgeToKeepTraversable o = new RemoveMaxEdgeToKeepTraversable();
        System.out.println(o.maxNumEdgesToRemove(4, new int[][]{
                new int[]{3, 1, 2},
                new int[]{3, 2, 3},
                new int[]{1, 1, 3},
                new int[]{1, 2, 4},
                new int[]{1, 1, 2},
                new int[]{2, 3, 4}
        }));
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind aliceUF = new UnionFind(n);
        UnionFind bobUF = new UnionFind(n);
        int ret = 0;
        Arrays.sort(edges, (o1, o2) -> o2[0] - o1[0]);
        for (int[] edge : edges) {
            switch (edge[0]) {
                case 1:
                    if (!aliceUF.union(edge[1] - 1, edge[2] - 1)) {
                        ret++;
                    }
                    break;
                case 2:
                    if (!bobUF.union(edge[1] - 1, edge[2] - 1)) {
                        ret++;
                    }
                    break;
                case 3:
                    boolean bobFlag = bobUF.union(edge[1] - 1, edge[2] - 1);
                    boolean aliceFlag = !aliceUF.union(edge[1] - 1, edge[2] - 1);
                    if (!bobFlag && aliceFlag) {
                        ret++;
                    }
                    break;
                default:
                    break;
            }
        }
        return aliceUF.count == 1 && bobUF.count == 1 ? ret : -1;
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
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * 将 p 和 q 归并到相同的分量中
         *
         * @param p 节点p
         * @param q 节点q
         */
        public boolean union(int p, int q) {
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
}
