package cn.dx.leetcode;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/24
 */
public class RepeatedSubString {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2 && s.length() % i ==0; i++) {
            // 子串的长度和整个字符串的长度是倍数关系，避免不需要检查的情况
            if(s.length() % i !=0){
                continue;
            }
            String sub = s.substring(0, i);
            // 子串长度为i，检查后面每个长度为i的串是否一样
            for (int j = i; j <= s.length() - i; j+=i) {
                if (sub.equals(s.substring(j,j+i))){
                    if (j+i == s.length()){
                        return true;
                    }
                }else {
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RepeatedSubString pss = new RepeatedSubString();
        System.out.println(pss.repeatedSubstringPattern("abab"));
        System.out.println(pss.repeatedSubstringPattern("abcabcabcabc"));
    }
}
