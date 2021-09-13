package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 959. 由斜杠划分区域
 * <p>
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * <p>
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * <p>
 * 返回区域的数目。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/25
 **/
public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf = new UnionFind(n * n * 4);
        for (int row = 0; row < n; row++) {
            String line = grid[row];
            char[] chars = line.toCharArray();
            for (int col = 0; col < n; col++) {
                char ch = chars[col];
                int num = row * (n * 4) + col * 4;
                if (ch == ' ') {
                    uf.union(num, num + 1);
                    uf.union(num + 1, num + 2);
                    uf.union(num + 2, num + 3);
                } else if (ch == '/') {
                    uf.union(num, num + 1);
                    uf.union(num + 2, num + 3);
                } else if (ch == '\\') {
                    uf.union(num + 1, num + 2);
                    uf.union(num, num + 3);
                }
                if (col - 1 >= 0) {
                    uf.union(num, num - 2);
                }
                if (row - 1 >= 0) {
                    uf.union(num + 1, (row - 1) * (n * 4) + col * 4 + 3);
                }
            }
        }
        for (int i = 0; i < uf.parent.length; i++) {
            uf.find(i);
        }
        System.out.println(Arrays.toString(uf.parent));
        return uf.count;
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
            //
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

    public static void main(String[] args) {
        RegionsCutBySlashes rcb = new RegionsCutBySlashes();
        System.out.println(rcb.regionsBySlashes(new String[]{" /", "/ "}));
        System.out.println(rcb.regionsBySlashes(new String[]{" /", "  "}));
        System.out.println(rcb.regionsBySlashes(new String[]{"\\/", "/\\"}));
    }
}

