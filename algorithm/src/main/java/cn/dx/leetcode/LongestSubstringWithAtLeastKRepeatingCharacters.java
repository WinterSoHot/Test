package cn.dx.leetcode;

/**
 * 395. 至少有K个重复字符的最长子串
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * <p>
 * 示例 1:
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/27
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    /**
     * 基于滑动窗口实现
     *
     * @param s 字符串
     * @param k 出现次数
     * @return 最长字串长度
     */
    public int longestSubstring1(String s, int k) {
        int ret = 0;
        int n = s.length();
        for (int i = 0; i < 26; i++) {
            // 限定左右范围内字符串的种类个数
            int l = 0, r = 0;
            // 维护范围内字符种类的数量
            int total = 0;
            // 用于判断当前范围字符串是否满足条件
            int less = 0;
            // 当前范围的字符出现次数记录
            int[] cnt = new int[26];
            while (r < n) {
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    // 第一次出现
                    total++;
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    // 当前字符达到条件
                    less--;
                }
                while (total > i) {
                    // 字符种类超出i，移动左指针
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        total--;
                        less--;
                    }
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    l++;
                }
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

    /**
     * 基于分治法实现
     * 找到当前范围内不满足条件的首个字符，以这个字符为界分治
     *
     * @param s 字符串
     * @param k 出现次数
     * @return 最长字串长度
     */
    public int longestSubstring(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    private int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            // 当前范围内都满足
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            // 以split作为分割，遍历两个子串，求出每个字串的结果
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            int len = dfs(s, start, i - 1, k);
            ret = Math.max(len, ret);
        }
        return ret;
    }
}
