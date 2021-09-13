package cn.dx.leetcode;

/**
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/19
 */
public class BackspaceCompare {
    public boolean backspaceCompare(String S, String T) {
        StringBuilder SB = new StringBuilder();
        StringBuilder TB = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == '#') {
                if (SB.length() > 0) {
                    SB.deleteCharAt(SB.length() - 1);
                }
            } else {
                SB.append(c);
            }
        }

        for (char c : T.toCharArray()) {
            if (c == '#') {
                if (TB.length() > 0) {
                    TB.deleteCharAt(TB.length() - 1);
                }
            } else {
                TB.append(c);
            }
        }
        return SB.toString().equals(TB.toString());
    }
}
