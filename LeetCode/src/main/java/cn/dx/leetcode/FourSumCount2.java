package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: medium
 *
 * @author dongxian
 * @version 0.1
 * @date 20-11-27
 **/
public class FourSumCount2 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // A B 全部组合和 保存 key: 和  value 出现次数
        // C D 全部组合和 如果存在上面保存的和的负数则增加val的次数
        int count = 0;
        Map<Integer, Integer> sum2Count = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                sum2Count.put(a + b, sum2Count.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                if (sum2Count.containsKey(-(c + d))) {
                    count += sum2Count.getOrDefault(-(c + d), 0);
                }
            }
        }
        return count;
    }
}
