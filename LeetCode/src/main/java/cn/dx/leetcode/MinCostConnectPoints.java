package cn.dx.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * <p>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/19
 **/
public class MinCostConnectPoints {
    /**
     * Kruskal 算法，对排序后的边，从小到大选择，将连接两个连通分量的边加入路径
     *
     * @param points 点集
     * @return 完全图的最小生成树
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        // 根据距离将边排序
        edges.sort(Comparator.comparingInt(o -> o.len));

        DisjointUnionSet dus = new DisjointUnionSet(n);
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            // 如果两个点不在一个连通分量中，则合并
            if (dus.unionSet(edge.x, edge.y)) {
                ret += edge.len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ret;
    }

    private int dist(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }
}

/**
 * 合并点，并且判断连通性
 * 并查集思想
 */
class DisjointUnionSet {
    int[] f;
    int[] rank;
    int n;

    public DisjointUnionSet(int n) {
        this.n = n;
        this.f = new int[n];
        this.rank = new int[n];
        Arrays.fill(rank, 1);
        for (int i = 0; i < n; i++) {
            this.f[i] = i;
        }
    }

    public int find(int x) {
        if (x != f[x]) {
            f[x] = find(f[x]);
        }
        return f[x];
    }

    public boolean unionSet(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return false;
        }
        if (rank[fx] < rank[fy]) {
            int tmp = fx;
            fx = fy;
            fy = tmp;
        }
        rank[fx] += rank[fy];
        f[fy] = fx;
        return true;
    }
}

class Edge {
    int len, x, y;

    public Edge(int len, int x, int y) {
        this.len = len;
        this.x = x;
        this.y = y;
    }
}
