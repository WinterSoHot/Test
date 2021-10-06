package cn.dx.leetcode;

/**
 * @author gudongxian
 * @date 2021/10/2
 */
public class IntegerToHex {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toHexString(-1));
    }
}
