package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. 分割回文串
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/7
 */
public class PalindromePartitioning {
    public boolean isPalindrome(String s) {
        int len = s.length() - 1;
        for (int i = 0; i <= len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i)) {
                return false;
            }
        }
        return true;
    }

    List<List<String>> ans = new LinkedList<>();
    List<String> path = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);
        return ans;
    }

    private void backtrack(String s, int start) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            if (isPalindrome(s.substring(start, i))) {
                path.add(s.substring(start, i));
                backtrack(s, i);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        System.out.println(pp.partition("aab"));
        pp.ans.clear();
        System.out.println(pp.partition("a"));
        pp.ans.clear();
        System.out.println(pp.partition("abccd"));
    }
}
