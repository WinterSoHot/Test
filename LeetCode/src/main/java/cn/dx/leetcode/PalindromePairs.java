package cn.dx.leetcode;

import java.util.*;

/**
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串
 * https://leetcode-cn.com/problems/palindrome-pairs/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/6
 */
public class PalindromePairs {
    public boolean isPalindrome(String a, int start, int end) {
        int len = end - start + 1;
        for (int i = 0; i < len / 2; i++) {
            if (a.charAt(start + i) != a.charAt(end - i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * @param words 输入字符串数组
     * @return 结果
     */
    public List<List<Integer>> palindromePairs(String[] words) {

        int n = words.length;
        List<String> wordsRev = new ArrayList<>();
        Map<String, Integer> indices = new HashMap<>();
        for (String word : words) {
            wordsRev.add(new StringBuffer(word).reverse().toString());
        }
        for (int i = 0; i < n; i++) {
            indices.put(wordsRev.get(i), i);
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(word, j, m - 1)) {
                    Integer leftId = indices.getOrDefault(word.substring(0, j), -1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int rightId = indices.getOrDefault(word.substring(j, m), -1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();
        pp.palindromePairs(new String[]{"a", "abc", "aba", ""});
    }
}
