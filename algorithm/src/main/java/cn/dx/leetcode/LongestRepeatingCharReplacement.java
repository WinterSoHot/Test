package cn.dx.leetcode;

/**
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * <p>
 * 注意：字符串长度 和 k 不会超过 104。
 * <p>
 * level: medium
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/2
 */
public class LongestRepeatingCharReplacement {
    public int characterReplacement(String s, int k) {
        // 保存当前区间数字出现的次数
        int[] num = new int[26];
        int n = s.length();
        int left = 0, right = 0;
        int maxn = 0;
        while (right < n) {
            // 当前字母出现次数加1
            int ch = s.charAt(right) - 'A';
            num[ch]++;
            // 比较谁是出现次数最大的字母
            maxn = Math.max(maxn, num[ch]);
            // 除了出现次数最多的，剩余字母个数大于k
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
