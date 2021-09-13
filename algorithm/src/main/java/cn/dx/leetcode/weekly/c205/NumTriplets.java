package cn.dx.leetcode.weekly.c205;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给你两个整数数组 nums1 和 nums2 ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：
 *
 *     类型 1：三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.length 且 0 <= j < k < nums2.length
 *     类型 2：三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k] 其中 0 <= i < nums2.length 且 0 <= j < k < nums1.length
 *
 * 大数测试用例不通过
 *
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/6
 */
public class NumTriplets {
    public int numTriplets(int[] nums1, int[] nums2) {
        List<Long> list = new ArrayList<>();
        for (int i : nums2) {
            list.add(i*1L);
        }
        int count = 0;
        for (int i : nums1) {
                count+=check(1L*i*i,list);
        }
        list.clear();
        for (int i : nums1) {
            list.add(i*1L);
        }
        for (int i : nums2) {
            count+=check(1L*i*i,list);
        }
        return count;
    }

    private int check(long pow, List<Long> nums) {
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            long num = nums.get(i);
            long target = pow / num;
            for (int j = i+1; j < nums.size(); j++) {
                long can = nums.get(j);
                if (can == target){
                    if (can * num == pow){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumTriplets nt = new NumTriplets();
        System.out.println(nt.numTriplets(new int[]{7,4},new int[]{5,2,8,9}));
    }
}
