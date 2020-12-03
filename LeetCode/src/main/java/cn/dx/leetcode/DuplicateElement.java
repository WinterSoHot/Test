package cn.dx.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * <p>
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class DuplicateElement {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
