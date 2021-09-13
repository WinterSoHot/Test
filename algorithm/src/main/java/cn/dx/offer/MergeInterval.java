package cn.dx.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/5
 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len == 0 || len == 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < l) {
                ans.add(new int[]{l, r});
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], r);
            }
        }

        return ans.toArray(new int[0][]);
    }
}
