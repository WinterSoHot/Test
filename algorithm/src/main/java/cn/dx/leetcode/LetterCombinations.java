package cn.dx.leetcode;

import java.util.*;

/**
 * LetterCombinations https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 类似排列组合问题，可以构建一颗树，对树进行遍历，路径上表示组合
 *
 * @author dongxian
 * @version 1.0
 * 20-6-3 下午10:07
 **/
public class LetterCombinations {
    Map<Character, String> keyMap = new HashMap<>();

    {
        keyMap.put('2', "abc");
        keyMap.put('3', "def");
        keyMap.put('4', "ghi");
        keyMap.put('5', "jkl");
        keyMap.put('6', "mno");
        keyMap.put('7', "pqrs");
        keyMap.put('8', "tuv");
        keyMap.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        findBranch(digits, 0, "", res);
        return res;
    }

    /**
     * 查找分支
     *
     * @param digits 源字符串
     * @param index  当前查看的字符串位置
     * @param s      之前查找的字符串
     * @param res    保存查找结果
     */
    private void findBranch(String digits, int index, String s, List<String> res) {
        // 查找结束
        if (s.length() == digits.length()) {
            res.add(s);
            return;
        }
        String mapLetters = keyMap.getOrDefault(digits.charAt(index), "");
        for (int i = 0; i < mapLetters.length(); i++) {
            findBranch(digits, index + 1, s + mapLetters.charAt(i), res);
        }
    }

    public static void main(String[] args) {
        LetterCombinations lc = new LetterCombinations();
        System.out.println(lc.letterCombinations(""));
    }
}
