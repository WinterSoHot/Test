package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/5
 **/
public class LongestSubString {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        int n = s.length();
        int ans = 0;
        int start = 0, end = 0;
        while (end < n) {
            char endChar = s.charAt(end);
            Integer endCount = charFrequency.getOrDefault(endChar, 0);
            charFrequency.put(endChar, endCount + 1);
            while (charFrequency.get(endChar) > 1) {
                char startChar = s.charAt(start);
                Integer startCount = charFrequency.getOrDefault(startChar, 0);
                if (startCount > 0) {
                    charFrequency.put(startChar, startCount - 1);
                }
                start++;
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestSubString lss = new LongestSubString();
        System.out.println(lss.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lss.lengthOfLongestSubstring("bbbbb"));
        System.out.println(lss.lengthOfLongestSubstring("pwwkew"));
        System.out.println(lss.lengthOfLongestSubstring(""));
    }
}
