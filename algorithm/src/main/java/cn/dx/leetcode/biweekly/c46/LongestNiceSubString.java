package cn.dx.leetcode.biweekly.c46;

import java.util.HashSet;
import java.util.Set;

/**
 * 5668. 最长的美好子字符串
 * <p>
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 * <p>
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class LongestNiceSubString {
    public String longestNiceSubstring(String s) {
        int len = s.length();
        String ans = "";
        for (int i = 0; i < len; i++) {
            Set<Character> set = new HashSet<>();
            int bad = 0;
            for (int j = i; j < len; j++) {
                char ch = s.charAt(j);
                if (!set.contains(ch)) {
                    if (Character.isLowerCase(ch)) {
                        if (!set.contains(Character.toUpperCase(ch))) {
                            bad++;
                        } else {
                            bad--;
                        }
                    } else {
                        if (!set.contains(Character.toLowerCase(ch))) {
                            bad++;
                        } else {
                            bad--;
                        }
                    }
                    set.add(ch);
                }
                if (bad == 0 && j - i + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
