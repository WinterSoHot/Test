package cn.dx.leetcode;

import java.util.Arrays;

/**
 * MinReorder TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-31 上午11:13
 **/
public class MinReorder {
    public int minReorder(int n, int[][] connections) {
        boolean[] flag = new boolean[n];
        Arrays.fill(flag, false);
        flag[0] = true;
        int count = 0;
        for (int[] connection : connections) {
            int start = connection[0];
            int end = connection[1];
            if (flag[start]) {
                if (!flag[end]){
                    count++;
                    flag[end] = true;
                }
            } else if (flag[end]) {
                flag[start] = true;
            }

        }
        return count;
    }
}
