package cn.dx.leetcode;

/**
 * 765. 情侣牵手
 * <p>
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 * <p>
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * <p>
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/14
 */
public class CoupleHoldingHands {
    public int minSwapsCouples(int[] row) {
        int m = row.length;
        int n = m / 2;
        /*
         * 每对情侣之间构成一个联通分量。
         * 如果两个相近得情侣不是一对，则表现为连通分量减少
         */
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i += 2) {
            uf.union(row[i] / 2, row[i + 1] / 2);
        }
        return n - uf.count;
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
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
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
        }
    }
}
