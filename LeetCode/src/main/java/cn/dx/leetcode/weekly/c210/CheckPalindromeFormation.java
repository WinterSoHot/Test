package cn.dx.leetcode.weekly.c210;

/**
 * 给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。
 * <p>
 * 当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。
 * <p>
 * 如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
 * <p>
 * 请注意， x + y 表示连接字符串 x 和 y 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-two-strings-to-make-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 题解
 * https://leetcode-cn.com/problems/split-two-strings-to-make-palindrome/solution/jiao-huan-dao-xu-wo-men-ju-jue-fen-qing-kuang-tao-/
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/11
 */
public class CheckPalindromeFormation {
    public boolean checkPalindromeFormation(String a, String b) {
        int left = a.length() / 2 - 1;
        // 中心扩展，a b长度一样
        // 找到中心满足回文串的最大子串
        left = Math.min(check(a, a, left), check(b, b, left));
        // 然后将 a b 拼接，找出left是否移动到 -1 ,则表示能够拼成回文串
        left = Math.min(check(a, b, left), check(b, a, left));
        return left == -1;
    }

    private int check(String a, String b, int left) {
        int right = a.length() - 1 - left;
        while (left >= 0 && right < a.length()) {
            if (a.charAt(left) != b.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return left;
    }


    public static void main(String[] args) {
        String a = "aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd", b = "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea";
        CheckPalindromeFormation cpf = new CheckPalindromeFormation();
        System.out.println(cpf.checkPalindromeFormation(a, b));
    }
}
