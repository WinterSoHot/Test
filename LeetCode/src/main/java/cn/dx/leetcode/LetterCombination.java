package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/26
 */
public class LetterCombination {

    Map<Character, String> num2Letters = new HashMap<>();
    List<String> ans = new ArrayList<>();
    List<String> temp = new ArrayList<>();

    {
        num2Letters.put('2', "abc");
        num2Letters.put('3', "def");
        num2Letters.put('4', "ghi");
        num2Letters.put('5', "jkl");
        num2Letters.put('6', "mno");
        num2Letters.put('7', "pqrs");
        num2Letters.put('8', "tuv");
        num2Letters.put('9', "wxyz");
    }

    public void addDigit(char digit) {
        String letters = num2Letters.get(digit);
        if (ans.size() == 0) {
            for (int i = 0; i < letters.length(); i++) {
                ans.add(letters.charAt(i) + "");
            }
        } else {
            temp.clear();
            for (int i = 0; i < letters.length(); i++) {
                for (String lastAns : ans) {
                    temp.add(new String(lastAns + letters.charAt(i)));
                }
            }
            ans = new ArrayList<>(temp);
        }
    }


    public List<String> letterCombinations(String digits) {
        for (int i = 0; i < digits.length(); i++) {
            addDigit(digits.charAt(i));
        }
        return ans;
    }
}
