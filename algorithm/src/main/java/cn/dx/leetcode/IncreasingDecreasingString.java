package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * <p>
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * <p>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-decreasing-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: low
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/11/25
 */
public class IncreasingDecreasingString {
    public String sortString(String s) {
        int[] ch2Count = new int[26];
        Arrays.fill(ch2Count, 0);
        for (char ch : s.toCharArray()) {
            ch2Count[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        // 上升标识
        boolean flag = true;
        while (sb.length() < s.length()) {
            if (flag) {
                for (int i = 0; i < 26; i++) {
                    if (ch2Count[i] != 0) {
                        sb.append((char) (i + 'a'));
                        ch2Count[i]--;
                    }
                }
                flag = false;
            } else {
                for (int i = 25; i >= 0; i--) {
                    if (ch2Count[i] != 0) {
                        sb.append((char) (i + 'a'));
                        ch2Count[i]--;
                    }
                }
                flag = true;
            }
        }
        return sb.toString();
    }
}
