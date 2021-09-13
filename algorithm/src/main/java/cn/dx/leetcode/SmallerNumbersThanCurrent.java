package cn.dx.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/26
 */
public class SmallerNumbersThanCurrent {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        // 暴力
        for (int i = 0; i < n; i++) {
            ret[i] = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[j] < nums[i]) {
                    ret[i] += 1;
                }
            }
        }
        //快速排序
        int[][] data = new int[n][2];
        for (int i = 0; i < nums.length; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, Comparator.comparingInt(o -> o[0]));
        int prev = -1;
        for (int i = 0; i < n; i++) {
            // 计算左边的个数
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            // data[i][1]是原来的位置
            ret[data[i][1]] = prev;
        }

        // 计数排序
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        for (int i = 1; i < 101; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ret;
    }
}
