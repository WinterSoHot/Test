package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1128. 等价多米诺骨牌对的数量
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * <p>
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * <p>
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * <p>
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/26
 **/
public class NumEquivDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<String, Integer> visited = new HashMap<>();
        int ans = 0;
        for (int[] dominoe : dominoes) {
            String key = String.format("%d-%d", dominoe[0], dominoe[1]);
            String revKey = String.format("%d-%d", dominoe[1], dominoe[0]);
            if (visited.containsKey(key)) {
                Integer val = visited.get(key);
                ans += val;
                visited.put(key, val + 1);
            } else if (visited.containsKey(revKey)) {
                Integer val = visited.get(revKey);
                ans += val;
                visited.put(revKey, val + 1);
            } else {
                visited.put(key, 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NumEquivDominoPairs nedp = new NumEquivDominoPairs();
        System.out.println(nedp.numEquivDominoPairs(new int[][]{
                new int[]{1, 1},
                new int[]{2, 2},
                new int[]{1, 1},
                new int[]{1, 2},
                new int[]{1, 2},
                new int[]{1, 1}
        }));
    }
}
