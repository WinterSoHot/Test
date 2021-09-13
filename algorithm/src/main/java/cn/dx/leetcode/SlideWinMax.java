package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 例 1：「力扣」第 239 题：滑动窗口的最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 作者：liweiwei1419
 * 链接：https://leetcode-cn.com/leetbook/read/learning-algorithms-with-leetcode/5wyxze/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/13
 */
public class SlideWinMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || n < k) {
            return new int[0];
        }
        int[] ret = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //判单队首元素是否移除
            if (i >= k && !q.isEmpty() && q.peekFirst() == i - k) {
                q.removeFirst();
            }
            //判断待添加的元素是否队首元素大
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.removeLast();
            }
            q.addLast(i);
            if (i >= k - 1) {
                ret[i - k + 1] = nums[q.peekFirst()];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        SlideWinMax swm = new SlideWinMax();
        System.out.println(Arrays.toString(swm.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(swm.maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(swm.maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
    }
}
