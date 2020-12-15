package cn.dx.leetcode;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0;
        int maxLen = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            while (s.substring(start, end).contains(ch + "")) {
                start++;
            }
            end++;
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }
}
