package cn.dx.leetcode;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/2
 */
public class IsNumber {

    public boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public boolean isNumber(String s) {
        // 去除两边空格
        s = s.trim();
        // 中间不能有空格
        if (s.contains(" ")) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        int EC = 0;
        // 操作符标识
        boolean opFlag = false;
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.toCharArray()[i];
            // 多个标识符
            if (c == '-' || c == '+') {
                if (opFlag) {
                    return false;
                }
                opFlag = true;
                continue;
            }
            opFlag = true;
            // E 操作符flag重置
            if (c == 'e' || c == 'E') {
                EC++;
                c = 'E';
                opFlag = false;
            }
            // 确保顶多一个E
            if (EC > 1) {
                return false;
            }
            // E后面不能有小数点
            if (c == '.' && EC == 1) {
                return false;
            }
            sb.append(c);
        }
        s = sb.toString();
        if (s.isEmpty()) {
            return false;
        }
        // 存在E的情况，分割判断
        if (s.contains("E")) {
            String[] split = s.split("E");
            if (split.length != 2) {
                return false;
            }
            for (String s1 : split) {
                if (s1.isEmpty()) {
                    return false;
                }
                if (!isNumber(s1)) {
                    return false;
                }
            }
            return true;
        }

        // 没有E的情况
        if (s.length() == 1 && ".".equals(s)) {
            return false;
        }
        // 小数点标识
        boolean smallNumFlag = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '.') {
                if (!smallNumFlag) {
                    smallNumFlag = true;
                    continue;
                } else {
                    return false;
                }
            }
            if (!isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsNumber in = new IsNumber();
//        System.out.println(in.isNumber("-1E-16"));
        System.out.println(in.isNumber("1  "));
    }
}
