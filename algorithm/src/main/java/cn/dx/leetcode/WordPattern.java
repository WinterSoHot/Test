package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. 单词规律
 * <p>
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/16
 **/
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        Map<Character, String> ch2Word = new HashMap<>();
        char[] chars = pattern.toCharArray();
        if (words.length != chars.length) {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch2Word.containsKey(ch)) {
                if (!words[i].equals(ch2Word.get(ch))) {
                    return false;
                }
            } else {
                if (ch2Word.containsValue(words[i])) {
                    return false;
                }
                ch2Word.put(ch, words[i]);
            }
        }

        return true;
    }
}
