package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 * <p>
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 * <p>
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: hard
 * <p>
 * 方法：枚举+生成树
 * 通过枚举每条边和最小生成树进行对比
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/21
 **/
public class FindCriticalAndPseudoCriticalEdges {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, Comparator.comparingInt(o -> o[2]));

        UnionFind uf = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < m; i++) {
            if (uf.union(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            // 判断是否关键边
            UnionFind uft = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; j++) {
                if (i != j && uft.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (uft.count != 1 || v > value) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }

            //判断是否伪关键边
            uft = new UnionFind(n);
            uft.union(newEdges[i][0], newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < m; j++) {
                if (i != j && uft.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (v == value) {
                ans.get(1).add(newEdges[i][3]);
            }
        }
        return ans;
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        int n;
        int count;

        public UnionFind(int n) {
            this.n = n;
            this.count = n;
            this.parent = new int[n];
            this.rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public boolean union(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (this.rank[fx] < this.rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            parent[fy] = fx;
            this.rank[fx] += this.rank[fy];
            --this.count;
            return true;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean connected(int x, int y) {
            return this.parent[x] == this.parent[y];
        }
    }

}
