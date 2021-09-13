package cn.dx.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/6
 */
public class NextGreatElement {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> indexStack = new Stack<>();
        int len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        for (int i = 0; i < 2 * len; i++) {
            if (indexStack.isEmpty()) {
                indexStack.push(i % len);
            } else {
                while (!indexStack.isEmpty() && nums[i % len] > nums[indexStack.peek()]) {
                    int preIndex = indexStack.pop();
                    ans[preIndex] = nums[i % len];
                }
                indexStack.push(i % len);
            }
        }
        return ans;
    }
}
