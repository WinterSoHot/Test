package cn.dx.leetcode;

/**
 * 992. K 个不同整数的子数组
 * <p>
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * <p>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * <p>
 * 返回 A 中好子数组的数目。
 * <p>
 * level: hard
 * <p>
 * 总结：双指针问题一般都带有最大最小前提条件
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/9
 **/
public class SubArrayWithDistinct {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistance(A, K) - atMostKDistance(A, K - 1);
    }

    /**
     * 最多有K个不同整数的子区间个数
     *
     * @param A 数组
     * @param k K个不同
     * @return 个数
     */
    private int atMostKDistance(int[] A, int k) {
        int len = A.length;
        int[] freq = new int[len + 1];
        int left = 0, right = 0;
        int count = 0;
        int res = 0;
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > k) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            res += right - left;
        }
        return res;
    }

    public static void main(String[] args) {
        SubArrayWithDistinct swd = new SubArrayWithDistinct();
        System.out.println(swd.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
        System.out.println(swd.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }
}
