package cn.dx.leetcode;

/**
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class PalindromeString {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        // s = s.replaceAll("[^a-z0-9]", "");
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
        }
        s = sb.toString();
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
