package cn.dx.leetcode.weekly.c202;

import java.util.Arrays;

/**
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * <p>
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * <p>
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * <p>
 * https://leetcode-cn.com/contest/weekly-contest-202/problems/magnetic-force-between-two-balls/
 * <p>
 * 题解：二分搜索
 * https://leetcode-cn.com/problems/magnetic-force-between-two-balls/solution/c-er-fen-sou-suo-ying-gai-neng-gei-ni-jiang-ming-b/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/16
 */
public class MagneticForce {
    /**
     * 检查当前数组以x为最小间隔能否满足m-1个间隔条件
     *
     * @param x        最小间隔
     * @param position 数组
     * @param m        点个数
     * @return 是否满足
     */
    public boolean check(int x, int[] position, int m) {
        // 间隔个数
        int cnt = 0;
        int target = position[0] + x;
        for (int i = 0; i < position.length - 1; i++) {
            // 如果 target 在当前两个节点直接，i+1 作为新的点，增加一个间隔
            if (position[i] < target && position[i + 1] >= target) {
                cnt++;
                target = position[i + 1] + x;
            }
        }
        // 如果间隔大于要求的间隔，说明满足条件
        return cnt >= m - 1;
    }


    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int len = position.length;
        // 数组中最大间隔
        int diff = position[len - 1] - position[0];
        // 数组中最小间隔
        int mn = diff;
        for (int i = 0; i < len - 1; i++) {
            if (mn > position[i + 1] - position[i]) {
                mn = position[i + 1] - position[i];
            }
        }
        if (m == 2) {
            return diff;
        }
        // 确定间隔大小左右边界，最小间隔和最大可能间隔
        int l = mn, r = diff / (m - 1);
        while (l <= r) {
            // 二分搜索，以当前间隔作为条件开始检查
            int mid = (l + r) / 2;
            if (check(mid, position, m)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l - 1;
    }
}
