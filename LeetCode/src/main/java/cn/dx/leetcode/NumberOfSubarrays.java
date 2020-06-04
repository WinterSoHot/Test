package cn.dx.leetcode;

/**
 * 最优子数组
 */
public class NumberOfSubarrays {
    public static void main(String[] args) {
        //TODO
        int[] nums = new int[]{1,1,2,1,1};
        int k = 3;
        int count = 0;
        for (int i = 0; i < nums.length - k; i++) {
            for (int j = k-1; j < nums.length; j++) {
                int tmpCount = 0;
                for (int l = i; l < j; l++) {
                    if (nums[l] % 2 ==1 ){
                        tmpCount++;
                    }
                    if (tmpCount>k){
                        break;
                    }
                }
                if (k == tmpCount) count += 1;
            }
        }
        System.out.println(count);
    }
}
