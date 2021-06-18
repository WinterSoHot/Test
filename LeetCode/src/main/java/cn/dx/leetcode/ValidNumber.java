package cn.dx.leetcode;

/**
 * 65. 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 * <p>
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/6/17
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        isDecimal = false;
        isScience = false;
        if (s.length() == 0) {
            return false;
        }
        return checkToken(s, 0);
    }

    boolean isDecimal = false;
    boolean isScience = false;

    public boolean checkToken(String s, int index) {
        if (index >= s.length()) return false;
        char ch = s.charAt(index);
        if (ch == '+' || ch == '-') {
            if (index + 1 < s.length() && (Character.isDigit(s.charAt(index + 1)) || s.charAt(index + 1) == '.')) {
                return checkNumber(s, index + 1);
            }
            return false;
        } else if (Character.isDigit(ch) || ch == '.') {
            return checkNumber(s, index);
        } else {
            return false;
        }
    }

    public boolean checkNumber(String s, int index) {
        if (index >= s.length()) {
            return true;
        }
        char ch = s.charAt(index);
        if (ch == 'e' || ch == 'E') {
            if (isScience) {
                return false;
            } else {
                isScience = true;
            }

            isDecimal = true;
            return checkToken(s, index + 1);
        } else if (Character.isDigit(ch)) {
            return checkNumber(s, index + 1);
        } else if (ch == '.') {
            if (isDecimal) {
                return false;
            } else {
                isDecimal = true;
            }
            // 检查前后，只要前后有一个是数字就行
            if (index - 1 >= 0 && Character.isDigit(s.charAt(index - 1))) {
                return checkNumber(s, index + 1);
            } else if (index + 1 < s.length() && Character.isDigit(s.charAt(index + 1))) {
                return checkNumber(s, index + 1);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        System.out.println("vn.isNumber(\"0\") = " + vn.isNumber("0"));
        System.out.println("vn.isNumber(\"e\") = " + vn.isNumber("e"));
        System.out.println("vn.isNumber(\".\") = " + vn.isNumber("."));
        System.out.println("vn.isNumber(\".1\") = " + vn.isNumber(".1"));
    }
}
