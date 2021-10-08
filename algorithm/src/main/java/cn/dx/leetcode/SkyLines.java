package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author gudongxian
 * @date 2021/10/7
 */
public class SkyLines {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = buildings.length;
        if (n == 0) {
            return ans;
        }
        for (int i = 0; i < n; i++) {
            int[] cur = buildings[i];
            if (i == 0) {
                ans.add(Arrays.asList(cur[0], cur[2]));
                continue;
            }
            int[] pre = buildings[i - 1];
            if (cur[2] == pre[2]) {
                continue;
            }
            if (cur[0] > pre[1]) {
                // 和前面不相交
                ans.add(Arrays.asList(pre[1], 0));
                ans.add(Arrays.asList(cur[0], cur[2]));
            } else if (cur[0] < pre[1] && cur[1] > pre[1]) {
                // 部分交接
                ans.add(Arrays.asList(cur[2] > pre[2] ? cur[0] : pre[1], cur[2]));
            } else {
                // 当前区域被前面区域包含
                if (cur[2] == pre[2]) {
                    continue;
                }
                ans.add(Arrays.asList(cur[0], cur[2]));
            }
        }
        ans.add(Arrays.asList(buildings[n - 1][1], 0));
        return ans;
    }

    public static void main(String[] args) {
        SkyLines skyLines = new SkyLines();
        System.out.println(skyLines.getSkyline(new int[][]{
                new int[]{2, 9, 10},
                new int[]{3, 7, 15},
                new int[]{5, 12, 12},
                new int[]{15, 20, 10},
                new int[]{19, 24, 8},
        }));

//        [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
    }
}
