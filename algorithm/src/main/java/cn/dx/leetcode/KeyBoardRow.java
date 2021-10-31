package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 500. 键盘行
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * @author gudongxian
 * @date 2021/10/31
 */
public class KeyBoardRow {
    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (isValid(word)) {
                ans.add(word);
            }
        }
        return ans.toArray(new String[]{});
    }


    String rowIdx = "12210111011122000010020202";

    private boolean isValid(String word) {
        if (word.isEmpty()) {
            return true;
        }
        char line = rowIdx.charAt(Character.toLowerCase(word.charAt(0)) - 'a');
        for (int i = 1; i < word.length(); i++) {
            if (rowIdx.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != line) {
                return false;
            }
        }
        return true;
    }
}
