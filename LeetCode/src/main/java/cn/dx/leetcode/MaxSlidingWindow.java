package cn.dx.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: hard
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/2
 **/
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
        // 保存到队列中，element ：[值，位置]
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            // 出堆
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            // 剩余最大值
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSlidingWindow msw = new MaxSlidingWindow();
        int[] ret = msw.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(ret));
        System.out.println(Arrays.toString(msw.maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(msw.maxSlidingWindow(new int[]{9, 11}, 2)));
    }
}
