package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author dongxian
 * @version 0.1
 * @date 20-11-23
 **/
public class MiniNumberOfArrowShots {
    public int findMinArrowShots(int[][] points) {
        if (points.length < 1) {
            return 0;
        }
        // 不能直接用 o1[1] - o2[1] 会导致溢出
        Arrays.sort(points, (o1, o2) -> {
            if (o1[1] < o2[1]) {
                return -1;
            } else if (o1[1] > o2[1]) {
                return 1;
            } else {
                return 0;
            }
        });
        int count = 1;
        int axis = points[0][1];
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i]);
            if (axis < points[i][0]) {
                count++;
                axis = points[i][1];
            }
        }
        return count;
    }
}
