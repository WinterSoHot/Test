package cn.dx.leetcode;

/**
 * 最大连续 1 的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * <p>
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xqr8m5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 双指针
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/3
 **/
public class LongestOnes {
    /**
     * 双指针，指向两个区间两侧，如果区间内可变化的数量大于K则左指针移动
     *
     * @param A 数组
     * @param K 最多改变
     * @return 最长
     */
    public int longestOnes(int[] A, int K) {
        int[] nums = new int[2];
        int left = 0;
        int right;
        // 常规思路：应该每次满足条件的区间判断大小
        // 内循环使用if判断，只移动一次，将区间最长的长度保存下来
        for (right = 0; right < A.length; right++) {
            nums[A[right]]++;
            if (nums[0] > K) {
                nums[A[left]]--;
                left++;
            }
        }
        return right - left;
    }

    public static void main(String[] args) {
        LongestOnes lo = new LongestOnes();
        System.out.println(lo.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(lo.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }
}
