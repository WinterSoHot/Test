package cn.dx.leetcode;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/12/18
 */
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        int[] ch2count = new int[26];
        for (char c : s.toCharArray()) {
            ch2count[c - 'a'] += 1;
        }
        for (char c : t.toCharArray()) {
            ch2count[c - 'a'] -= 1;
        }
        for (int i = 0; i < ch2count.length; i++) {
            if (ch2count[i] == -1) {
                return (char) (i + 'a');
            }
        }
        return 'a';
    }
}
