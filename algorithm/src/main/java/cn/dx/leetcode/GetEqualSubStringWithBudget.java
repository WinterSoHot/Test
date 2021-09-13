package cn.dx.leetcode;

/**
 * 1208. 尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 * <p>
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 * <p>
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * <p>
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * <p>
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/5
 **/
public class GetEqualSubStringWithBudget {

    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int[] diff = new int[len];
        for (int i = 0; i < len; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int start = 0, end = 0;
        int ans = 0;
        int sum = 0;
        while (end < len) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start++];
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }


    public static void main(String[] args) {
        GetEqualSubStringWithBudget gsw = new GetEqualSubStringWithBudget();
        System.out.println(gsw.equalSubstring("abcd", "bcdf", 3));
        System.out.println(gsw.equalSubstring("abcd", "cdef", 3));
        System.out.println(gsw.equalSubstring("abcd", "acde", 0));
    }
}
