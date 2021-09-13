package cn.dx.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/contest/weekly-contest-192/problems/shuffle-the-array/
 */
public class Shuffle {
    public int[] shuffle(int[] nums, int n) {
        int [] res = new int[nums.length];
        int curIndex = 0;
        for (int i = 0; i < n; i++) {
            res[curIndex] = nums[i];
            res[curIndex+1] = nums[i+n];
            curIndex+=2;
        }
        return res;
    }

    public static void main(String[] args) {
        Shuffle sf = new Shuffle();
        System.out.println(Arrays.toString(sf.shuffle(new int[]{1,3,4,5},2)));
    }
}
