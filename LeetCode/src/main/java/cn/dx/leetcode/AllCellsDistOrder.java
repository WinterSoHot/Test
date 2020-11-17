package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author dongxian
 * @version 0.1
 * @date 20-11-17
 **/
public class AllCellsDistOrder {
    /**
     * 直接排序
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ans = new int[R * C][];
        int index = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans[index++] = new int[]{i, j};
            }
        }
        Arrays.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0);
                int dis2 = Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0);
                return dis1 - dis2;
            }
        });
        return ans;
    }

    /**
     * 桶排序
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int maxDist = Math.max(r0, Math.abs(R - 1 - r0)) + Math.max(c0, Math.abs(C - 1 - c0));
        List<List<int[]>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int d = dist(i, j, r0, c0);
                buckets.get(d).add(new int[]{i, j});
            }
        }
        int[][] ans = new int[R * C][];
        int index = 0;
        for (List<int[]> bucket : buckets) {
            for (int[] it : bucket) {
                ans[index++] = it;
            }
        }
        return ans;
    }

    /**
     * Stream 形式排序
     */
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        return IntStream.range(0, R).boxed()
                .flatMap(r -> IntStream.range(0, C).mapToObj(c -> new int[]{r, c}))
                .sorted((pos1, pos2) -> dist(pos1[0], pos1[1], r0, c0) - dist(pos2[0], pos2[1], r0, c0))
                .toArray(int[][]::new);
    }

    private int dist(int i, int j, int r0, int c0) {
        return Math.abs(i - r0) + Math.abs(j - c0);
    }
}
