package cn.dx.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class ReverseSentenceWord {
    boolean[] visited;

    public ArrayList<String> Permutation(String str) {
        visited = new boolean[str.length()];
        int n = str.length();
        Set<String> ans = new HashSet<>();
        if (n == 0) {
            return new ArrayList<>(ans);
        }
        StringBuilder sb = new StringBuilder();
        char[] chs = str.toCharArray();
        backtrack(chs, sb, visited, ans);
        ArrayList<String> ret = new ArrayList<>(ans);
        Collections.sort(ret);
        return ret;
    }

    private void backtrack(char[] chs, StringBuilder sb, boolean[] visited, Set<String> ans) {
        if (sb.length() == chs.length) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < chs.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(chs[i]);
            backtrack(chs, sb, visited, ans);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    public String ReverseSentence(String str) {
        if (str.isEmpty() || "".equals(str.trim())) {
            return str;
        }
        String[] s = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = s.length; i > 0; i--) {
            sb.append(s[i - 1]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
