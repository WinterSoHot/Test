package cn.dx.offer;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class LeftMoveString {
    public String LeftRotateString(String str, int n) {
        if (str.isEmpty()) {
            return str;
        }
        n = n % str.length();
        if (n == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        String sub = sb.substring(0, n);
        sb.delete(0, n);
        sb.append(sub);
        return sb.toString();
    }
}
