package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 公司共有 n 个项目和  m 个小组，每个项目要不无人接手，要不就由 m 个小组之一负责。
 * <p>
 * group[i] 表示第 i 个项目所属的小组，如果这个项目目前无人接手，那么 group[i] 就等于 -1。（项目和小组都是从零开始编号的）小组可能存在没有接手任何项目的情况。
 * <p>
 * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
 * <p>
 * 同一小组的项目，排序后在列表中彼此相邻。
 * 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。
 * 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/12
 **/
public class SortItem {
    /**
     * 要求：
     * 1. 同一个小组的项目，排序后在列表中相邻
     * 2. 项目之间存在以来关系
     * <p>
     * =》
     * <p>
     * 小组之间存在依赖关系
     * <p>
     * 项目之间也存在依赖关系
     * <p>
     * 故，先求出组之间的依赖关系，在对组内的项目进行排序
     *
     * @param n           项目个数
     * @param m           项目小组
     * @param group       项目所属小组
     * @param beforeItems 项目之间的依赖关系
     * @return 排序结果
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // 组之间和组内的图结构
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < n + m; i++) {
            groupGraph.add(new ArrayList<>());
        }
        List<List<Integer>> itemGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        // 组之间和组内的入度数组
        int[] groupDegree = new int[n + m];
        int[] itemDegree = new int[n];

        List<Integer> id = new ArrayList<>();
        for (int i = 0; i < n + m; i++) {
            id.add(i);
        }
        // 组和项目之间
        List<List<Integer>> groupItem = new ArrayList<>();
        for (int i = 0; i < n + m; i++) {
            groupItem.add(new ArrayList<>());
        }
        // 重新编号，将原来为-1的重新编号
        int leftId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = leftId;
                leftId++;
            }
            groupItem.get(group[i]).add(i);
        }

        // 构建依赖关系图
        for (int i = 0; i < n; i++) {
            // 当前项目对应的组编号
            int curGroup = group[i];
            // 项目之间的依赖关系
            for (Integer item : beforeItems.get(i)) {
                // 得到依赖项目的组编号
                int beforeGroupId = group[item];
                if (beforeGroupId == curGroup) {
                    // 同组的则加入到组内图中
                    itemDegree[i] += 1;
                    itemGraph.get(item).add(i);
                } else {
                    // 不同组加入到组间图中
                    groupDegree[curGroup] += 1;
                    groupGraph.get(beforeGroupId).add(curGroup);
                }
            }
        }

        // 组间拓扑关系排序
        List<Integer> groupTopSort = topSort(groupDegree, groupGraph, id);
        if (groupTopSort.size() == 0) {
            return new int[0];
        }
        int[] ans = new int[n];
        int index = 0;
        // 组内拓扑关系排序
        for (int curGroupId : groupTopSort) {
            // 当前组的编号
            int size = groupItem.get(curGroupId).size();
            if (size == 0) {
                continue;
            }
            // 对当前组内的项目进行拓扑排序
            List<Integer> res = topSort(itemDegree, itemGraph, groupItem.get(curGroupId));
            if (res.size() == 0) {
                return new int[0];
            }
            for (int item : res) {
                ans[index++] = item;
            }
        }
        return ans;
    }

    /**
     * 拓扑排序
     *
     * @param deg   每个点的入度
     * @param graph 图结构
     * @param items 当前排序的点
     * @return 排序结果
     */
    public List<Integer> topSort(int[] deg, List<List<Integer>> graph, List<Integer> items) {
        Queue<Integer> queue = new LinkedList<>();
        for (Integer item : items) {
            if (deg[item] == 0) {
                queue.offer(item);
            }
        }
        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for (Integer v : graph.get(u)) {
                if (--deg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res.size() == items.size() ? res : new ArrayList<>(0);
    }
}

