package cn.dx.leetcode;

/**
 *
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 *
 * https://leetcode-cn.com/problems/string-rotation-lcci/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/4
 */
public class StringRotation {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length()!=s2.length()){
            return false;
        }
        if (s1.length() == 0){
            return true;
        }
        s2 += s2;

//        int [] ne = new int[s1.length()];
//
//        // 构建剩余匹配表
//        for (int i = 1,j=0; i < s1.length(); i++) {
//            if (s1.charAt(i)==s1.charAt(j)){
//                j++;
//            }else {
//                j=0;
//            }
//            ne[i] = j;
//        }
//
//        // 找子串
//        for (int i = 1, j = 0; i <= s2.length(); i++) {
//            if (s2.charAt(i - 1) == s1.charAt(j)){
//                j ++ ;
//            }
//            if (j == s1.length()) {
//                return true;
//            }
//        }
        return s2.contains(s1);
    }

    public static void main(String[] args) {
        String s = "ABCDABD";
        int [] ne = new int[s.length()];
        for (int i = 1,j=0; i < s.length(); i++) {
            if (s.charAt(i)==s.charAt(j)){
                j++;
            }else {
                j=0;
            }
            ne[i] = j;
        }
        for (int i : ne) {
            System.out.println(i + "\t");
        }
    }
}
