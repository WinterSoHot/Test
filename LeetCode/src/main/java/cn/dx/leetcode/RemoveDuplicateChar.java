package cn.dx.leetcode;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/9
 */
public class RemoveDuplicateChar {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            if (top >= 0 && sb.charAt(top) == S.charAt(i)) {
                sb.deleteCharAt(top);
                top--;
            } else {
                sb.append(S.charAt(i));
                top++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateChar rdc = new RemoveDuplicateChar();
        System.out.println(rdc.removeDuplicates("abbaca"));
    }
}
