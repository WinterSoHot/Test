package cn.dx.leetcode;

/**
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * @author gudongxian
 * @date 2021/10/7
 */
public class CountOfWord {
    public int countSegments(String s) {
        int ans = 0;
        boolean preWord = false;
        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                if (preWord) {
                    ans++;
                    preWord = false;
                }
                continue;
            }
            preWord = true;
        }
        if (preWord) {
            ans++;
        }
        return ans;
    }
}
