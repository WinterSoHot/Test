package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1631. 最小体力消耗路径
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * <p>
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * <p>
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/29
 */
public class PathWithMinEffort {

    int[] dirX = {-1, 0, 1, 0};
    int[] dirY = {0, -1, 0, 1};

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int n = row * col;

        // 构建边
        List<int[]> edges = new ArrayList<>();
        int maxThreshold = 0;
        int minThreshold = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                for (int i = 0; i < 4; i++) {
                    int adjX = x + dirX[i];
                    int adjY = y + dirY[i];
                    if (adjX >= 0 && adjY >= 0 && adjX < row && adjY < col) {
                        int threshold = Math.abs(heights[x][y] - heights[adjX][adjY]);
                        edges.add(new int[]{x * col + y, adjX * col + adjY, threshold});
                        maxThreshold = Math.max(maxThreshold, threshold);
                        minThreshold = Math.min(minThreshold, threshold);
                    }
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        // 根据最大最小阈值二分查找最小满足结果的threshold
        int left = minThreshold, right = maxThreshold;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 使用并查集判断最后首尾节点的连通性
            UnionFind uf = new UnionFind(n);
            int v = 0;
            for (int[] edge : edges) {
                if (edge[2] <= mid) {
                    uf.union(edge[0], edge[1]);
                    v = Math.max(v, edge[2]);
                }
            }
            if (uf.connected(0, row * col - 1)) {
                right = mid - 1;
                ret = Math.min(ret, v);
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
