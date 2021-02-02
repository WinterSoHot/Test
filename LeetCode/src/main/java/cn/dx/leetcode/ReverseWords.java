package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * <p>
 * 作者：画手大鹏
 * 链接：https://leetcode-cn.com/leetbook/read/illustrate-lcof/59e6xm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/2
 */
public class ReverseWords {
    public String reverseWords(String s) {
        s = s.trim();
        int j = s.length() - 1;
        int i = j;
        List<String> words = new ArrayList<>();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            words.add(s.substring(i + 1, j));
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }
        return String.join(" ", words);
    }

    public String reverseWords2(String s) {
        String[] words = s.trim().split(" +");
        int left = 0, right = words.length - 1;
        while (left < right) {
            String tmpWord = words[right];
            words[right] = words[left];
            words[left] = tmpWord;
            left++;
            right--;
        }
        return String.join(" ", words);
    }
}
