package cn.dx.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一次只出现一次的字符
 * <p>
 * 题目描述
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> char2Count = new HashMap<>();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char2Count.put(str.charAt(i), char2Count.getOrDefault(str.charAt(i), 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (char2Count.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
