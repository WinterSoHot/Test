package cn.dx.leetcode.weekly.c205;

import java.util.*;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/6
 */
public class MinCost {

    private int v = 0;
    private List<Integer> ans = new ArrayList<>();
    private Set<String> visited = new HashSet<>();

    public int minCost(String s, int[] cost) {
        Map<Integer, Integer> start2end = new HashMap<>();
        for (int i = 0; i < s.length();i++) {
            int key = i;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    start2end.put(key, j);
                    i = j;
                    continue;
                }
                break;
            }
        }
        for (Map.Entry<Integer, Integer> entry : start2end.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int i = start; i <= end; i++) {
                sum += cost[i];
                if (cost[i] > max) {
                    max = cost[i];
                }
            }
            v += (sum - max);

        }
        return v;

//        int[] removed = new int[s.length()];
//        backtrack(s, removed, cost);
//        return ans.stream().mapToInt(value -> value).min().getAsInt();
    }

    private void backtrack(String s, int[] removed, int[] cost) {

        // 检查当前情况是否检查过
        if (visited.contains(toStr(removed))) {
            return;
        }
        visited.add(toStr(removed));

        // 检查是否满足要求
        int pos = check(s, removed);
        if (pos == -1) {
            ans.add(v);
            return;
        }

        // 对不满足要求的节点进行扩散
        for (int i = pos; i < s.length() && s.charAt(i) == s.charAt(pos); i++) {
            if (removed[i] == 1) {
                continue;
            }
            //  选择
            removed[i] = 1;
            v += cost[i];
            // 迭代
            backtrack(s, removed, cost);
            // 撤销
            removed[i] = 0;
            v -= cost[i];
        }
    }

    public int check(String s, int[] removed) {
        Map<Character, Integer> ch2idx = new HashMap<>();
        for (int i = 0; i < removed.length; i++) {
            if (removed[i] != 1) {
                if (ch2idx.containsKey(s.charAt(i))) {
                    return ch2idx.get(s.charAt(i));
                } else {
                    ch2idx.clear();
                    ch2idx.put(s.charAt(i), i);
                }
            }
        }
        return -1;
    }

    public String toStr(int[] removed) {
        StringBuilder sb = new StringBuilder();
        for (int i : removed) {
            sb.append(i);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MinCost mc = new MinCost();
//        System.out.println(mc.minCost("abaac"
//                , new int[]{1, 2, 3, 4, 5}));
//        System.out.println(mc.minCost("abc"
//                , new int[]{1, 2, 3}));
//        System.out.println(mc.minCost("aabaa"
//                , new int[]{1, 2, 3, 4, 1}));
        System.out.println(mc.minCost("aaabbbabbbb",
                new int[]{3,5,10,7,5,3,5,5,4,8,1}));
//        System.out.println(mc.minCost("bbbaaa",
//        new int[]{4,9,3,8,8,9}));

//        System.out.println(mc.minCost("cddcdcae",
//                new int[]{4,8,8,4,4,5,4,2}));
    }

}
