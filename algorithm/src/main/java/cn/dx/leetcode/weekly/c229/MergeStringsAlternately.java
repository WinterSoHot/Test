package cn.dx.leetcode.weekly.c229;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class MergeStringsAlternately {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int index1 = 0;
        int index2 = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        while (index1 < len1 && index2 < len2) {
            sb.append(word1.charAt(index1));
            sb.append(word2.charAt(index2));
            index1++;
            index2++;
        }
        if (index1 < len1) {
            sb.append(word1.substring(index1));
        }
        if (index2 < len2) {
            sb.append(word2.substring(index2));
        }
        return sb.toString();
    }
}
