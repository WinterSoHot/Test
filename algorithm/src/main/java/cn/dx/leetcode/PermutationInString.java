package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 567. 字符串的排列
 * <p>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/10
 **/
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int n = s2.length();
        int windowSize = s1.length();
        int[] num4Count1 = new int[26];
        int[] num4Count2 = new int[26];
        Arrays.fill(num4Count1, 0);
        Arrays.fill(num4Count2, 0);
        for (int i = 0; i < windowSize; i++) {
            num4Count1[s1.charAt(i) - 'a']++;
            num4Count2[s2.charAt(i) - 'a']++;
        }
        for (int i = windowSize; i < n; i++) {
            if (Arrays.equals(num4Count1, num4Count2)) {
                return true;
            }
            num4Count2[s2.charAt(i - windowSize) - 'a']--;
            num4Count2[s2.charAt(i) - 'a']++;
        }
        return Arrays.equals(num4Count1, num4Count2);
    }

    public static void main(String[] args) {
        PermutationInString pis = new PermutationInString();
        System.out.println(pis.checkInclusion("hello", "ooolleoooleh"));
    }
}
