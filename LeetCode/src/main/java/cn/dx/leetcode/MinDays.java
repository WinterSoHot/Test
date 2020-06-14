package cn.dx.leetcode;

import java.util.Arrays;

/**
 * MinDays https://leetcode-cn.com/contest/weekly-contest-193/problems/minimum-number-of-days-to-make-m-bouquets/
 *
 * @author dongxian
 * @version 1.0
 * 20-6-14 上午11:08
 **/
public class MinDays {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int maxDay = Arrays.stream(bloomDay).max().getAsInt();
        int minDay = Arrays.stream(bloomDay).min().getAsInt();
        for (int i = minDay; i <= maxDay; i++) {
            int tmpM = 0;
            int tmpK = k;
            for (int i1 : bloomDay) {
                if (tmpM == m){
                    break;
                }
                if (i1 > i) {
                    tmpK = k;
                } else {
                    tmpK--;
                    if (tmpK == 0) {
                        tmpM++;
                        tmpK = k;
                    }
                }
            }
            if (tmpM == m)   {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MinDays md = new MinDays();
        int res = md.minDays(new int[]{1000000000,1000000000}
                , 1, 1);
        System.out.println(res);
    }
}
