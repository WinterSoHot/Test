package cn.dx.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/contest/weekly-contest-192/problems/the-k-strongest-values-in-an-array/
 */
public class GetStrongest {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int [] res = new int[k];
        int i,j;
        i = 0;
        j = arr.length -1;
        int midNum = arr[(arr.length-1)/2];
        for (int x = 0; x < k; x++) {
            if (Math.abs(arr[i] - midNum) > Math.abs(arr[j] - midNum)){
                res[x] = arr[i];
                i++;
            }else {
                res[x] = arr[j];
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GetStrongest gs = new GetStrongest();
        System.out.println(Arrays.toString(gs.getStrongest(new int[]{1,1,3,5,5},2)));
    }
}
