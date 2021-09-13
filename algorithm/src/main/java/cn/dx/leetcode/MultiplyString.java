package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * https://leetcode-cn.com/problems/multiply-strings/
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/13
 */
public class MultiplyString {
    /**
     * 做乘法
     *
     * 两数相乘，结果长度不超过两数长度之和
     * 用数组保存每次乘得结果
     *         1 2 3
     *         4 5 6
     *     6  12  18
     *   5 10 15
     * 4 8 12
     * ---------------
     * 4 13 28 27 18
     * 进位
     * 5 6 0 8 8
     * @param num1 参数1
     * @param num2 参数2
     * @return 乘法结果字符串
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int n1Len = num1.length();
        int n2Len = num2.length();
        int len = n1Len + n2Len;
        int [] res = new int[len];
        Arrays.fill(res,0);
        // 模拟竖排乘法
        for (int i = n2Len - 1; i >= 0; i--) {
            int ch1 = num2.charAt(i) - '0';
            for (int j = n1Len - 1; j >=0 ; j--) {
                int ch2 = num1.charAt(j) - '0';
                int idx = len - 2 - (i + j);
                res[idx] += (ch1 * ch2);
            }
        }
        // 进位
        for (int i = 0; i < len - 1; i++) {
            res[i+1] += res[i] / 10;
            res[i] %= 10;
            // 将进位后得当前结果添加到字符串中
            sb.append(res[i]);
        }
        // 别忘记最后一位
        sb.append(res[len - 1]);
        // 删除多于得0
        for (int i = sb.length() - 1; i >= 0 ; i--) {
            if (sb.charAt(i) == '0'){
                sb.deleteCharAt(i);
                continue;
            }
            break;
        }
        // 翻转返回结果
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        MultiplyString ms = new MultiplyString();
        String multiply = ms.multiply("9", "9");
        System.out.println(multiply);
    }
}
