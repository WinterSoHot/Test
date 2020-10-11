package cn.dx.leetcode.weekly.c210;

import java.util.LinkedList;
import java.util.List;

/**
 * n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
 * <p>
 * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
 * <p>
 * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
 * <p>
 * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-network-rank
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/11
 */
public class MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int startCount = graph.get(i).size();
                int endCount = graph.get(j).size();
                int curCount = startCount + endCount;
                if (graph.get(i).contains(j)) {
                    curCount -= 1;
                }
                res = Math.max(curCount, res);
            }
        }
        return res;
    }
}
