package cn.dx.leetcode;

/**
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (num > nums[j]) {
                    count = Math.max(count, dp[j]);
                }
            }
            dp[i] = count + 1;
        }

        for (Integer value : dp) {
            if (value >= 3) {
                return true;
            }
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        if (nums.length < 3) return false;
        int min = Integer.MAX_VALUE; //最小值
        int second = Integer.MAX_VALUE;//第二元素最小的递增对的第二元素
        for (int num : nums) {
            if (num <= min) {//num比min小或相等，肯定不存在递增三元素。由于不存在以num结尾的递增对，故只需更新min
                min = num;
            } else if (num <= second) {//num比second小或相对，肯定不存在递增三元素。由于存在以num结尾的递增对且num不大于second，因此可以更新second
                second = num;
            } else {//num比second大，那就存在递增三元素，因为second是一个递增对的第二元素，加上num后就是递增三元素了
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        IncreasingTriplet it = new IncreasingTriplet();
        System.out.println(it.increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println(it.increasingTriplet(new int[]{5, 1, 5, 5, 2, 5, 4}));
        System.out.println(it.increasingTriplet(new int[]{2, 1, 5, 0, 3}));
    }
}
