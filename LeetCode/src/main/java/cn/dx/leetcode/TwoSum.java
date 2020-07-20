package cn.dx.leetcode;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * 167. 两数之和 II - 输入有序数组
 */
public class TwoSum {
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
