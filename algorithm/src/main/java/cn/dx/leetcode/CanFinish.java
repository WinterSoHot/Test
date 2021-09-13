package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author gudongxian
 * @version 0.1
 * @date 8/4/2020
 *
 * https://leetcode-cn.com/problems/course-schedule/solution/ke-cheng-biao-by-leetcode-solution/
 */
public class CanFinish {
    /**
     * 保存节点之间的边
     */
    List<List<Integer>> edges;
    /**
     * 标记节点状态, 0表示未访问，1表示搜索中，2搜索完成
     */
    int [] visted;

    boolean vaild = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visted = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses && vaild; i++) {
            if (visted[i] == 0){
                dfs(i);
            }
        }
        return vaild;
    }

    private void dfs(int u) {
        visted[u] = 1;
        for (Integer v : edges.get(u)) {
            if (visted[v] == 0){
                dfs(v);
                if (!vaild){
                    return;
                }
            }else if (visted[v] == 1){
                // 当前u节点在搜索过程中遇到v也在搜索中，表示u和v之间构成环
                vaild = false;
                return;
            }
        }
        visted[u] = 2;
    }
}
