package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 1482. 制作 m 束花所需的最少天数
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * <p>
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * <p>
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * <p>
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/9
 */
public class MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n) {
            return -1;
        }
        int left = Arrays.stream(bloomDay).min().getAsInt();
        int right = Arrays.stream(bloomDay).max().getAsInt();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(bloomDay, mid, m, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] bloomDay, int mid, int m, int k) {
        int cm = 0;
        int bloomCount = 0;
        for (int bloom : bloomDay) {
            if (mid >= bloom) {
                bloomCount++;
                if (bloomCount == k) {
                    cm++;
                    bloomCount = 0;
                }
            } else {
                bloomCount = 0;
            }
        }
        return cm >= m;
    }
}
