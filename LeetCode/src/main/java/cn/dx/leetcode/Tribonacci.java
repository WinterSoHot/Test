package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class Tribonacci {
    Map<Integer, Integer> table = new HashMap<>();

    {
        table.put(0, 0);
        table.put(1, 1);
        table.put(2, 1);
    }

    public int tribonacci(int n) {
        if (table.containsKey(n)) {
            return table.get(n);
        }
        int cur = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        table.put(n, cur);
        return cur;
    }
}
