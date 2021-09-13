package cn.dx.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 547. 省份数量
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * level: medium
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/7
 */
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        Set<Integer> set = new HashSet<>();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }
            DFS(i, isConnected, set);
            ret++;
        }
        return ret;
    }

    private void DFS(int i, int[][] isConnected, Set<Integer> set) {
        if (set.contains(i)) {
            return;
        }
        set.add(i);
        for (int j = 0; j < isConnected[i].length; j++) {
            if (isConnected[i][j] == 1) {
                DFS(j, isConnected, set);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces nop = new NumberOfProvinces();
        int ret = nop.findCircleNum(new int[][]{
                new int[]{1, 0, 0},
                new int[]{0, 1, 0},
                new int[]{0, 0, 1}
        });
        System.out.println(ret);
    }
}
