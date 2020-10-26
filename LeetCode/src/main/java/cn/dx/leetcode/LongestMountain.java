package cn.dx.leetcode;

/**
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * <p>
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * <p>
 * 如果不含有 “山脉” 则返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 * <p>
 * 方法：枚举山顶
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/25
 */
public class LongestMountain {
    public int longestMountain(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        // 当前节点往左边扩展得个数
        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            // 递推
            left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
        }
        // 当前节点往右边扩展得个数
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            // 递推
            right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestMountain lm = new LongestMountain();
        int ret = lm.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5});
        int ret2 = lm.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5, 1, 2, 4, 5, 6, 7, 8, 7, 6});
        System.out.println(ret);
        System.out.println(ret2);
    }
}
