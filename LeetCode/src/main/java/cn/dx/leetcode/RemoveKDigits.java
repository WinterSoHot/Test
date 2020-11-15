package cn.dx.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 * <p>
 * 方法：找出句子中第一个左边字符大于右边字符的地方，移除左边字符。不存在则移除最后一个元素
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/15
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(num);

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < sb.length() - 1; j++) {
                if (sb.charAt(j) > sb.charAt(j + 1)) {
                    sb.deleteCharAt(j);
                    break;
                }
                if (j + 1 == sb.length() - 1) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }

        int zeroHead = -1;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') {
                break;
            }
            zeroHead = i;
        }
        sb.delete(0, zeroHead + 1);
        return sb.length() > 0 ? sb.toString() : "0";
    }

    public String removeKdigits2(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int len = num.length();
        for (int i = 0; i < len; i++) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits rkd = new RemoveKDigits();
        String res = rkd.removeKdigits("1432219", 3);
        System.out.println(res);
        res = rkd.removeKdigits("10200", 1);
        System.out.println(res);
        res = rkd.removeKdigits("10", 1);
        System.out.println(res);
    }
}
