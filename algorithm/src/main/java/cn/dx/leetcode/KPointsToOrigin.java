package cn.dx.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/9
 */
public class KPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        List<int[]> res = Arrays.stream(points).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = o1[0] * o1[0] + o1[1] * o1[1];
                int dis2 = o2[0] * o2[0] + o2[1] * o2[1];
                return dis1 - dis2;
            }
        }).collect(Collectors.toList());
        int[][] ret = new int[K][2];
        for (int i = 0; i < K; i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
