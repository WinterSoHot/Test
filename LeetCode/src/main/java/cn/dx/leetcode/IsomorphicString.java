package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/12/27
 */
public class IsomorphicString {
    public boolean isIsomorphic(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 != len2) {
            return false;
        }
        Map<Character, Character> ch2ch = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (!ch2ch.containsKey(ch1) && !ch2ch.containsValue(ch2)) {
                ch2ch.put(ch1, ch2);
            } else {
                if (ch2ch.containsKey(ch1)) {
                    if (ch2ch.get(ch1) != ch2) {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicString is = new IsomorphicString();
        System.out.println(is.isIsomorphic("ab", "aa"));
        System.out.println(is.isIsomorphic("egg", "add"));
        System.out.println(is.isIsomorphic("foo", "bar"));
    }
}
