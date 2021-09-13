package cn.dx.leetcode;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * level: hard
 * <p>
 * 分析：每个位置能够接受的雨水和左右两边最近的高度有关
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/4
 **/
public class TrappingRainWater {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }
        int ans = 0;
        // 记录当前左右两边的最高位置
        int leftMax = height[0];
        int rightMax = height[len - 1];
        int left = 1, right = len - 2;
        // 双指针，两边移动
        while (left <= right) {
            int minVal = Math.min(leftMax, rightMax);
            if (minVal == leftMax) {
                if (minVal > height[left]) {
                    ans += minVal - height[left];
                }
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                if (minVal > height[right]) {
                    ans += minVal - height[right];
                }
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TrappingRainWater trw = new TrappingRainWater();
        System.out.println(trw.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trw.trap(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(trw.trap(new int[]{3, 2, 4}));
    }
}
