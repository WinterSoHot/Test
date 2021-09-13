package cn.dx.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：困难
 * <p>
 * 纯回溯：31/36 超时
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/1
 */
public class WordBreak2 {
    List<String> res = new LinkedList<>();
    Deque<String> path = new LinkedList<>();
    int wordMaxLen = 0;
    int wordMinLen = Integer.MAX_VALUE;

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            wordMaxLen = Math.max(wordMaxLen, word.length());
            wordMinLen = Math.min(wordMinLen, word.length());
        }
        backtrack(s, wordDict);
        return res;
    }

    private void backtrack(String curs, List<String> wordDict) {
        // 结束
        if (curs.isEmpty()) {
            res.add(String.join(" ", path));
            return;
        }
        for (int i = wordMinLen; i <= wordMaxLen && i <= curs.length(); i++) {
            String word = curs.substring(0, i);
            if (wordDict.contains(word)) {
                path.add(word);
                backtrack(curs.substring(i), wordDict);
                path.removeLast();
            }
        }
    }


}
