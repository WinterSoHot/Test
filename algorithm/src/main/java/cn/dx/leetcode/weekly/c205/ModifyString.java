package cn.dx.leetcode.weekly.c205;

/**
 * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s<var> </var>，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
 * <p>
 * 注意：你 不能 修改非 '?' 字符。
 * <p>
 * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
 * <p>
 * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
 * <p>
 * https://leetcode-cn.com/contest/weekly-contest-205/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/6
 */
public class ModifyString {
    public String modifyString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '?') {
                // 保证不连续
                int start = -1;
                int end = 26;
                if (i - 1 >= 0) {
                    start = sb.charAt(i - 1) - 'a' ;
                }
                if (i + 1 < s.length()) {
                    if (s.charAt(i+1)!='?'){
                        end = s.charAt(i + 1) - 'a' ;
                    }
                }
                for (int i1 = 0; i1 < 26; i1++) {
                    if (i1 !=  start && i1 != end) {
                        sb.append((char) ('a' + i1));
                        break;
                    }
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ModifyString ms = new ModifyString();
        System.out.println(ms.modifyString("??yw?ipkj?"));
    }
}
