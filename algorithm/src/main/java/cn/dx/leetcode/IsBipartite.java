package cn.dx.leetcode;

/**
 * 判断二分图
 * <p>
 * 存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
 * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
 * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 * <p>
 * 如果图是二分图，返回 true ；否则，返回 false 。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xtek1g/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/27
 */
public class IsBipartite {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] nodes = graph[i];
            for (int node : nodes) {
                if (connect(p, i, node)) {
                    return false;
                }
                union(p, node, nodes[0]);
            }
        }
        return true;
    }

    private void union(int[] p, int x, int y) {
        int fx = find(p, x);
        int fy = find(p, y);
        if (fx != fy) {
            p[fx] = fy;
        }
    }

    private boolean connect(int[] p, int x, int y) {
        return find(p, x) == find(p, y);
    }

    private int find(int[] p, int x) {
        int f = p[x];
        if (f != x) {
            p[x] = find(p, f);
        }
        return p[x];
    }
}
