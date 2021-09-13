package cn.dx.leetcode;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * https://leetcode-cn.com/problems/palindromic-substrings/
 * <p>
 * 输入的字符串长度不会超过 1000 。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/19
 */
public class PalindromicSubString {

    /**
     * 计算字符串以idx为中心的回文串个数
     * @param s 字符串
     * @param idx  idx \in [0,2*s.length()-1]
     * @return 回文串个数
     */
    public int countPalindrom(String s, Integer idx) {
        int count = 0;
        int maxRadius = Math.min(idx, 2 * s.length() - idx - 2);
        // 如果idx在字符串上，则当前位置计数+1
        count = idx % 2 == 0 ? 1 : 0;
        // 如果idx在字符串上，则radius从2开始，否找从1开始，确保能够查看两边的字符
        int i = idx % 2 == 0 ? 2 : 1;
        // 步长为2，因为字符串间隔考虑在内
        for (; i <= maxRadius; i += 2) {
            // 除以2回到字符串位置上
            int left = (idx - i) / 2;
            int right = (idx + i) / 2;
            if (s.charAt(left) == s.charAt(right)) {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    public int countSubstrings(String s) {
        int sumCount = 0;
        for (int i = 0; i < 2 * s.length(); i++) {
            sumCount += countPalindrom(s, i);
        }
        return sumCount;
    }

    public static void main(String[] args) {
        PalindromicSubString pss = new PalindromicSubString();
        System.out.println(pss.countPalindrom("abc", 0));
        System.out.println(pss.countPalindrom("abc", 1));
        System.out.println(pss.countPalindrom("abc", 2));
        System.out.println(pss.countPalindrom("abc", 3));
        System.out.println(pss.countPalindrom("abc", 4));
        System.out.println(pss.countPalindrom("abc", 5));
        // 3
        System.out.println(pss.countSubstrings("abc"));
        // 6
        System.out.println(pss.countSubstrings("aaa"));
        // 4
        System.out.println(pss.countSubstrings("aba"));
    }
}
