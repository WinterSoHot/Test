package cn.dx.leetcode;

/**
 * 778. 水位上升的泳池中游泳
 * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * <p>
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * <p>
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 * <p>
 * level: hard
 * <p>
 * 并查集 二分搜索 深度优先搜索
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/30
 */
public class SwimInRisingWater {
    int[] dirX = {-1, 0, 1, 0};
    int[] dirY = {0, -1, 0, 1};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                min = Math.min(grid[i][j], min);
                max = Math.max(grid[i][j], max);
            }
        }
        int maxT = max - min;
        int left = 0, right = maxT;
        int ret = maxT;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 使用并查集判断最后首尾节点的连通性
            UnionFind uf = new UnionFind(n * n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cur = grid[i][j];
                    //如果水位比当前高度高
                    if (cur < mid) {
                        cur = mid;
                    }
                    // 四周
                    for (int idx = 0; idx < 4; idx++) {
                        int othX = i + dirX[idx];
                        int othY = j + dirY[idx];
                        if (othY >= 0 && othX >= 0 && othX < n && othY < n) {
                            int other = grid[othX][othY];
                            if (other < mid) {
                                other = mid;
                            }
                            if (Math.abs(other - cur) == 0) {
                                // 可以游过去
                                uf.union(i * n + j, othX * n + othY);
                            }
                        }
                    }
                }
            }
            // 判断是否可行
            if (uf.connected(0, n * n - 1)) {
                right = mid - 1;
                ret = Math.min(ret, mid);
            } else {
                left = mid + 1;
            }

        }
        return ret;
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
