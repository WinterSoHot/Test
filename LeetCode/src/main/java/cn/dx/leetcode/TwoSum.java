package cn.dx.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * 167. 两数之和 II - 输入有序数组
 */
public class TwoSum {

    Map<Integer, Integer> map = new HashMap<>();

    public int[] twoSum2(int[] numbers, int target) {

        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])){
                res[0] = map.get(target - numbers[i]);
                res[1] = i;
                return res;
            }
            map.put(numbers[i], i);
        }
        return res;
    }

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {

    }
}
