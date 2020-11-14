package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你两个数组，arr1 和 arr2，
 * <p>
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/14
 */
public class RelativeSortedArray {
    /**
     * 自定义排序方法
     *
     * @param arr1 数组
     * @param arr2 数组
     * @return 数组
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        List<Integer> arr2List = new ArrayList<>(arr2.length);
        for (int i : arr2) {
            arr2List.add(i);
        }
        List<Integer> list = Arrays.stream(arr1).boxed().sorted((o1, o2) -> {
            if (arr2List.contains(o1) && arr2List.contains(o2)) {
                return arr2List.indexOf(o1) - arr2List.indexOf(o2);
            } else if (!arr2List.contains(o1) && !arr2List.contains(o2)) {
                return o1 - o2;
            } else if (!arr2List.contains(o1)) {
                return 1;
            } else if (!arr2List.contains(o2)) {
                return -1;
            }
            return 0;
        }).collect(Collectors.toList());
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 计数排序
     *
     * @param arr1 数组
     * @param arr2 数组
     * @return 数组
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int i : arr1) {
            upper = Math.max(upper, i);
        }
        int[] frequency = new int[upper + 1];
        for (int i : arr1) {
            frequency[i] = frequency[i] + 1;
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; i++) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; x++) {
            for (int i = 0; i < frequency[x]; i++) {
                ans[index++] = x;
            }
        }
        return ans;
    }
}