package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 难度：中等
 * <p>
 * 方法：动态规划
 * <p>
 * 状态转移方程，考虑函数f_i 表示 s 0..i 之间存在的解码方式
 * 如果当前选择i作为解码则 f_i += f_{i-1}  s[i] 可以解码
 * 如果能选两个解码 f_i += f_{i-2}  s[i-1:i] 可以解码
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/21
 */
public class NumDecoding {
    int res = 0;

    Map<String, Integer> str2Count = new HashMap<>();

    {
        str2Count.put("1", 1);
        str2Count.put("2", 1);
        str2Count.put("3", 1);
        str2Count.put("4", 1);
        str2Count.put("5", 1);
        str2Count.put("6", 1);
        str2Count.put("7", 1);
        str2Count.put("8", 1);
        str2Count.put("9", 1);
        str2Count.put("10", 1);
        str2Count.put("11", 2);
        str2Count.put("12", 2);
        str2Count.put("13", 2);
        str2Count.put("14", 2);
        str2Count.put("15", 2);
        str2Count.put("16", 2);
        str2Count.put("17", 2);
        str2Count.put("18", 2);
        str2Count.put("19", 2);
        str2Count.put("20", 1);
        str2Count.put("21", 2);
        str2Count.put("22", 2);
        str2Count.put("23", 2);
        str2Count.put("24", 2);
        str2Count.put("25", 2);
        str2Count.put("26", 2);
    }

    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (str2Count.containsKey(s)) {
            return str2Count.get(s);
        }
        int res = 0;
        int ch = s.charAt(0) - '0';
        if (ch > 0) {
            res += numDecodings(s.substring(1));
            if (s.length() > 2) {
                ch = ch * 10 + s.charAt(1) - '0';
                if (ch > 0 && ch < 27) {
                    res += numDecodings(s.substring(2));
                }
            }
        }
        str2Count.put(s, res);
        return res;
    }

    public int numDecodings2(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) - '0' != 0) {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0') <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }


    public static void main(String[] args) {
        NumDecoding nd = new NumDecoding();
        System.out.println("nd.numDecodings(\"12\") = " + nd.numDecodings("12"));
        nd.res = 0;
        System.out.println("nd.numDecodings(\"226\") = " + nd.numDecodings("226"));
        nd.res = 0;
        System.out.println("nd.numDecodings(\"0\") = " + nd.numDecodings("0"));
        nd.res = 0;
        System.out.println("nd.numDecodings(\"06\") = " + nd.numDecodings("06"));
        nd.res = 0;
        System.out.println("nd.numDecodings(\"11111111111\") = " + nd.numDecodings("11111111111"));
    }
}
