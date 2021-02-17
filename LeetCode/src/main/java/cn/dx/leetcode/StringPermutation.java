package cn.dx.leetcode;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/17
 */
public class StringPermutation {
    public String[] permutation(String s) {
        int len = s.length();
        boolean[] visited = new boolean[len];
        Arrays.fill(visited, false);
        List<String> ret = new ArrayList<>();
        // 回溯
        backtrack(ret, visited, s, new StringBuilder());
        return new HashSet<>(ret).toArray(new String[0]);
    }

    private void backtrack(List<String> ret, boolean[] visited, String s, StringBuilder sb) {
        if (sb.length() == s.length()) {
            // 结束
            ret.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            // 当前字符串是否使用
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(s.charAt(i));

            backtrack(ret, visited, s, sb);

            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    public String[] permutation2(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[0]);
    }

    List<String> res = new ArrayList<>();
    char[] c;

    /**
     * 当前固定x位
     *
     * @param x 固定当前字符串位置
     */
    void dfs(int x) {
        if (x == c.length - 1) {
            res.add(new String(c));
            return;
        }
        // 标志当前位置是否固定重复
        Set<Character> has = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (has.contains(c[i])) {
                // 当前位置已经固定减少重复：剪枝
                continue;
            }
            has.add(c[i]);
            // 交换
            swap(i, x);
            dfs(x + 1);
            // 复原
            swap(i, x);
        }
    }

    void swap(int x, int y) {
        char tmp = c[x];
        c[x] = c[y];
        c[y] = tmp;
    }

    public static void main(String[] args) {
        StringPermutation sp = new StringPermutation();
        System.out.println(Arrays.toString(sp.permutation("abc")));
        System.out.println(Arrays.toString(sp.permutation2("abc")));
    }
}
