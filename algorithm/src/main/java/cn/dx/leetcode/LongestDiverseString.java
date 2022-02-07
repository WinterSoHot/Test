package cn.dx.leetcode;

import java.util.PriorityQueue;

/**
 * @author gudongxian
 * @date 2022/2/7
 */
public class LongestDiverseString {
    private String ans = "";

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        if (a > 0) {
            q.add(new int[]{0, a});
        }
        if (b > 0) {
            q.add(new int[]{1, b});
        }
        if (c > 0) {
            q.add(new int[]{2, c});
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 1) - 'a' == cur[0] && sb.charAt(n - 2) - 'a' == cur[0]) {
                if (q.isEmpty()) {
                    break;
                }
                int[] next = q.poll();
                sb.append((char) (next[0] + 'a'));
                if (--next[1] != 0) {
                    q.add(next);
                }
                q.add(cur);
            } else {
                sb.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0) {
                    q.add(cur);
                }
            }
        }
        return sb.toString();
    }


    public String longestDiverseString2(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        ans = "";
        deepFirstSearch(sb, a, b, c, ' ', ' ');
        return ans;
    }

    private void deepFirstSearch(StringBuilder sb, int a, int b, int c, char lastSecondChar, char lastChar) {
        if (sb.length() > ans.length()) {
            ans = sb.toString();
        }
        int curLength = sb.length();
        if (a > 0) {
            if (lastSecondChar != 'a' || lastChar != 'a') {
                sb.append('a');
                deepFirstSearch(sb, a - 1, b, c, lastChar, 'a');
                sb.deleteCharAt(curLength);
            }
        }
        if (b > 0) {
            if (lastSecondChar != 'b' || lastChar != 'b') {
                sb.append('b');
                deepFirstSearch(sb, a, b - 1, c, lastChar, 'b');
                sb.deleteCharAt(curLength);
            }
        }
        if (c > 0) {
            if (lastSecondChar != 'c' || lastChar != 'c') {
                sb.append('c');
                deepFirstSearch(sb, a, b, c - 1, lastChar, 'c');
                sb.deleteCharAt(curLength);
            }
        }
    }


    public static void main(String[] args) {
        LongestDiverseString main = new LongestDiverseString();
        System.out.println(main.longestDiverseString(1, 1, 7));
        System.out.println(main.longestDiverseString(2, 2, 1));
        System.out.println(main.longestDiverseString(7, 1, 0));
    }
}
