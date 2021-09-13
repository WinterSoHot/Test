package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/12
 */
public class SortArrayByParityII {
    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        List<Integer> odds = new LinkedList<>();
        List<Integer> evens = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (i % 2 == A[i] % 2) {
                continue;
            }
            if (i % 2 == 0) {
                odds.add(i);
            } else {
                evens.add(i);
            }
        }
        for (int i = 0; i < odds.size(); i++) {
            int idx1 = odds.get(i);
            int idx2 = evens.get(i);
            int tmp = A[idx1];
            A[idx1] = A[idx2];
            A[idx2] = tmp;
        }

        return A;
    }

    /**
     * 双指针
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII2(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }
}
