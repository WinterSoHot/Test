package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
 * <p>
 * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
 * <p>
 * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/sum-of-distances-in-tree/
 * <p>
 * 难度：困难
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/6
 */
public class SumOfDistancesInTree {
    int[] ans;
    int[] sz;
    int[] dp;
    List<List<Integer>> graph;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        ans = new int[N];
        // sz 记录以当前节点为根节点得子节点数量
        sz = new int[N];
        // dp 记录以当前节点为根节点的他的所有子节点的距离和
        dp = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        // 遍历当前u的所有直接子节点
        for (int v : graph.get(u)) {
            if (v == f) {
                continue;
            }
            dfs(v, u);
            // 等于 子节点的dp + sz
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    public void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v : graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }

}
