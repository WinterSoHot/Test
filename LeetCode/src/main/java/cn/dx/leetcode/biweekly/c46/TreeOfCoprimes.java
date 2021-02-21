package cn.dx.leetcode.biweekly.c46;

import java.util.*;

/**
 * 5670. 互质树
 * <p>
 * 给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，每个节点有一个值。树的 根节点 为 0 号点。
 * <p>
 * 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。nums[i] 表示第 i 个点的值，edges[j] = [uj, vj] 表示节点 uj 和节点 vj 在树中有一条边。
 * <p>
 * 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。
 * <p>
 * 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。一个节点 不是 它自己的祖先节点。
 * <p>
 * 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的 ，如果不存在这样的祖先节点，ans[i] 为 -1 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class TreeOfCoprimes {
    public int[] getCoprimes(int[] nums, int[][] edges) {
        int len = nums.length;
//        int[] parent = new int[len];
        List<List<Integer>> parents = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            parents.add(new ArrayList<>());
        }
//        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            parents.get(edge[1]).add(edge[0]);
        }
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        for (int i = 0; i < len; i++) {
//            int p = parent[i];
            List<Integer> ps = parents.get(i);
            Deque<Integer> q = new ArrayDeque<>(ps);
            while (!q.isEmpty()) {
                for (int s = 0; s < q.size(); s++) {
                    int p = q.poll();
                    if (gcd(nums[i], nums[p]) == 1) {
                        ans[i] = p;
                        break;
                    }
                    // TODO
                }
            }
        }
        return ans;
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
