package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * <p>
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * <p>
 * 注意，您的结果中 不应 包含任何括号。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/26
 */
public class ReverseSubStringWithParenthese {
    public String reverseParentheses(String s) {
        Deque<String> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                q.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, q.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseSubStringWithParenthese rsswp = new ReverseSubStringWithParenthese();
        System.out.println(rsswp.reverseParentheses("(abcd)"));
        System.out.println(rsswp.reverseParentheses("(u(love)i)"));
        System.out.println(rsswp.reverseParentheses("(ed(et(oc))el)"));
    }
}
