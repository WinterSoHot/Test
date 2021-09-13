package cn.dx.leetcode;

/**
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * <p>
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/20
 **/
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // 记录当前字符是否已经出现过
        boolean[] vis = new boolean[26];
        int[] nums = new int[26];
        for (char ch : s.toCharArray()) {
            nums[ch - 'a']++;
        }
        // 维护入站出站
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                // 没有出现过才考虑
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    // 如果栈顶元素大于当前元素
                    if (nums[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        // 如果当前栈顶元素剩余相同元素个数大于0,则移除当前元素
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                // 将当前元素添加进去
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            // 当前元素剩余个数减少
            nums[ch - 'a'] -= 1;
        }
        return sb.toString();
    }
}
