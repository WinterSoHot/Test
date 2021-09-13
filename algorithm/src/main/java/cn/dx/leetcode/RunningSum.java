package cn.dx.leetcode;

/**
 * RunningSum TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-6-14 上午10:50
 **/
public class RunningSum {
    public int[] runningSum(int[] nums) {
        int [] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
               res[i] = res[i-1]+nums[i];
        }
        return res;
    }
}
