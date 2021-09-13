package cn.dx.leetcode;

/**
 * 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 * <p>
 * 一块砖直接连接到网格的顶部，或者
 * 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 * <p>
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 * <p>
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bricks-falling-when-hit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level : hard
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/16
 **/
public class BrickFallWhenHit {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int h = grid.length, w = grid[0].length;
        UnionFind uf = new UnionFind(h * w + 1);
        int[][] status = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                status[i][j] = grid[i][j];
            }
        }
        // 逆向考虑，将打碎变成补全，补一个之后多了的个数就是打碎后掉的个数
        for (int[] hit : hits) {
            status[hit[0]][hit[1]] = 0;
        }

        // 初始并查集
        // h * w 表示特殊的节点，表示顶点和这个点相连
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i][j] == 1) {
                    if (i == 0) {
                        //在顶部
                        // h*w 默认是顶部相关的特殊顶点
                        uf.union(h * w, i * w + j);
                    }
                    if (i > 0 && status[i - 1][j] == 1) {
                        // 上面能够连接
                        uf.union(i * w + j, (i - 1) * w + j);
                    }
                    if (j > 0 && status[i][j - 1] == 1) {
                        // 左边能够连接
                        uf.union(i * w + j, i * w + j - 1);
                    }
                }
            }
        }

        // 模拟逆向操作
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] ret = new int[hits.length];
        // 正向，前面的操作对后面有影响
        // 进行逆向操作
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            // 如果当前位置在原来的地方就没有砖块
            if (grid[r][c] == 0) {
                continue;
            }

            // 没有补全之前的节点个数
            int prev = uf.size(h * w);
            if (r == 0) {
                // 如果是顶点
                uf.union(c, h * w);
            }

            // 考虑四个方向
            for (int[] direction : directions) {
                int nr = r + direction[0], nc = c + direction[1];
                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    // 和补全的地方相连，且当前地方为砖块
                    if (status[nr][nc] == 1) {
                        uf.union(r * w + c, nr * w + nc);
                    }
                }
            }

            // 补全之后的节点大小
            int size = uf.size(h * w);
            ret[i] = Math.max(0, size - prev - 1);
            status[r][c] = 1;
        }
        return ret;
    }

    /**
     * 并查集
     */
    class UnionFind {
        /**
         * 父节点
         */
        int[] f;
        /**
         * 树节点个数
         */
        int[] sz;

        public UnionFind(int n) {
            f = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
                sz[i] = 1;
            }
        }

        public int find(int x) {
            if (f[x] != x) {
                f[x] = find(f[x]);
            }
            return f[x];
        }

        public void union(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return;
            }
            f[fx] = fy;
            sz[fy] += sz[fx];
        }

        public int size(int x) {
            return sz[find(x)];
        }
    }
}
