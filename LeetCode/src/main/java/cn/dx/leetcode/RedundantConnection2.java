package cn.dx.leetcode;

import java.util.*;

/**
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 * <p>
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * <p>
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 * <p>
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：困难
 * <p>
 * 方法：图论， 出度和入度，根据题目，根节点的入度为0，其中每个节点的入度不应该大于1
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/17
 */
public class RedundantConnection2 {

    /**
     * 入度
     */
    Map<Integer, Integer> inMap = new HashMap<>();
    /**
     * 出度
     */
    Map<Integer, Integer> outMap = new HashMap<>();

    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 计算每个节点的入度
        for (int[] edge : edges) {
            if (!inMap.containsKey(edge[0])) {
                inMap.put(edge[0], 0);
            }
            if (!outMap.containsKey(edge[1])) {
                outMap.put(edge[1], 0);
            }
            inMap.put(edge[1], inMap.getOrDefault(edge[1], 0) + 1);
            outMap.put(edge[0], outMap.getOrDefault(edge[0], 0) + 1);
        }


        // 情况1
        int key = 0;
        // 检查是否存在一个节点有两个父节点
        for (Map.Entry<Integer, Integer> entry : inMap.entrySet()) {
            if (entry.getValue() == 2) {
                key = entry.getKey();
                break;
            }
        }
        System.out.printf("key: %d%n", key);
        if (key != 0) {
            for (int i = edges.length - 1; i >= 0; i--) {
                int[] edge = edges[i];
                if (edge[1] == key) {
                    // 确保删除后，开始节点不能变成孤立节点
                    if (outMap.get(edge[0]) > 1 || inMap.get(edge[0]) > 0) {
                        if (checkConn(edges, i, inMap)) {
                            return edge;
                        }
                    }

                }
            }
        } else {
            // 构建成环
            for (int i = edges.length - 1; i >= 0; i--) {
                int[] edge = edges[i];
                int end = edge[1];
                if (outMap.get(end) > 0 && inMap.get(end) == 1) {
                    return edge;
                }
            }
        }
        return null;
    }

    /**
     * 检查数组中的边的点是否全部连通
     *
     * @param edges 边
     * @param i     删除第i条边
     * @param inMap 入度大小
     * @return 是否连通
     */
    private boolean checkConn(int[][] edges, int i, Map<Integer, Integer> inMap) {
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();

        // 删除第i条边后，使用键值对表示边信息
        for (int k = 0; k < edges.length; k++) {
            int[] edge = edges[k];
            if (k == i) {
                continue;
            }
            List<Integer> value = edgesMap.getOrDefault(edge[0], new LinkedList<>());
            value.add(edge[1]);
            edgesMap.put(edge[0], value);
        }

        Set<Integer> allNodes = new HashSet<>();
        Set<Integer> connNodes = new HashSet<>();
        for (int[] edge : edges) {
            allNodes.add(edge[0]);
            allNodes.add(edge[1]);
        }

        int root = -1;
        for (Map.Entry<Integer, Integer> entry : inMap.entrySet()) {
            if (entry.getValue() == 0) {
                root = entry.getKey();
            }
        }
        // 没有找到根节点
        if (root == -1) {
            return false;
        }
        // 深度搜素
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int key = q.poll();
            connNodes.add(key);
            for (Integer newKey : edgesMap.getOrDefault(key, new LinkedList<>())) {
                if (!connNodes.contains(newKey)) {
                    q.offer(newKey);
                }
            }
        }
        // 检查深度搜素后的点是否等于全部节点
        return connNodes.size() == allNodes.size();
    }

    public static void main(String[] args) {
        RedundantConnection2 rc = new RedundantConnection2();
//        int [][] edges = new int[][]{new int[]{1,2}, new int[]{1,3}, new int[]{2,3}};
//        int [][] edges = new int[][]{new int[]{1,2}, new int[]{2,3}, new int[]{3,4},new int[]{4,1}, new int[]{1,5}};
//        int [][] edges = new int[][]{new int[]{2,1},new int[]{3,1},new int[]{4,2},new int[]{1,4}};
        int[][] edges = new int[][]{new int[]{4, 2}, new int[]{1, 5}, new int[]{5, 2}, new int[]{5, 3}, new int[]{2, 4}};
        int[] res = rc.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(res));
        System.out.println(rc.inMap);
        System.out.println(rc.outMap);
    }
}
