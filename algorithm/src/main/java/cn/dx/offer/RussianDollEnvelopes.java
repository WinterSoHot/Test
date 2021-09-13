package cn.dx.offer;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题
 * <p>
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/4
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (o1, o2) -> {
            int w1 = o1[0], h1 = o1[1];
            int w2 = o2[0], h2 = o2[1];
            if (w1 != w2) {
                return w1 - w2;
            } else {
                return h2 - h1;
            }
        });
        int[] f = new int[len];
        int count = 1;
        Arrays.fill(f, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            count = Math.max(count, f[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes rde = new RussianDollEnvelopes();
        int res = rde.maxEnvelopes(new int[][]{
                new int[]{5, 4},
                new int[]{6, 4},
                new int[]{6, 7},
                new int[]{2, 3}
        });
        System.out.println(res);
    }
}
