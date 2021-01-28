package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * <p>
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * <p>
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/15
 **/
public class MostStonesRemoved {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int ans = 0;
        boolean[] vis = new boolean[n];
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    edges.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans += 1;
                deepFirstSearch(edges, i, vis);
            }
        }
        return n - ans;
    }

    private void deepFirstSearch(List<List<Integer>> edges, int i, boolean[] vis) {
        vis[i] = true;
        for (Integer y : edges.get(i)) {
            if (!vis[y]) {
                deepFirstSearch(edges, y, vis);
            }
        }
    }

    /**
     * 并查集做法
     * <p>
     * 将同行同列看成连通分量，连通分量肯定能删除到只剩一个
     *
     * @param stones
     * @return
     */
    public int removeStones2(int[][] stones) {
        int n = stones.length;
        // 找到每个连通分量构成环的
        int nodeCount = stones.length;
        // 并查集 因为题目中的节点从1开始编号
        int[] parent = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    if (find(parent, i) != find(parent, j)) {
                        union(parent, i, j);
                        nodeCount--;
                    }
                }
            }
        }
        return n - nodeCount;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (index != parent[index]) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    public static void main(String[] args) {
        MostStonesRemoved msr = new MostStonesRemoved();
        int[][] stones = new int[][]{
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{1, 0},
                new int[]{1, 2},
                new int[]{2, 1},
                new int[]{2, 2}
        };
        System.out.println(msr.removeStones2(stones));
    }
}
