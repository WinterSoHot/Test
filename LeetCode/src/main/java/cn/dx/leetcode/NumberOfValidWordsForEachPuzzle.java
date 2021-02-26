package cn.dx.leetcode;

import java.util.*;

/**
 * 1178. 猜字谜
 * <p>
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * <p>
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * <p>
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */
public class NumberOfValidWordsForEachPuzzle {
    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            // 计算当前word的二进制表示
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0;

            // 枚举子集方法一
            // for (int choose = 0; choose < (1 << 6); ++choose) {
            //     int mask = 0;
            //     for (int i = 0; i < 6; ++i) {
            //         if ((choose & (1 << i)) != 0) {
            //             mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
            //         }
            //     }
            //     mask |= (1 << (puzzle.charAt(0) - 'a'));
            //     if (frequency.containsKey(mask)) {
            //         total += frequency.get(mask);
            //     }
            // }

            // 枚举子集方法二
            // 计算puzzle的二进制表示
            int mask = 0;
            for (int i = 1; i < 7; ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
                // 确保第一位字符每次都存在
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask;
            } while (subset != mask);
            ans.add(total);
        }
        return ans;

    }

    /**
     * 直接使用集合判断  通过 9/10
     *
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int wordCount = words.length;
        int puzzleCount = puzzles.length;
        List<Set<Character>> index2WordSet = new ArrayList<>(wordCount);
        for (int i = 0; i < wordCount; i++) {
            Set<Character> set = new HashSet<>();
            for (char c : words[i].toCharArray()) {
                set.add(c);
            }
            index2WordSet.add(set);
        }
        List<Integer> ans = new ArrayList<>(puzzleCount);
        for (int i = 0; i < puzzleCount; i++) {
            String puzzle = puzzles[i];
            int count = 0;
            Character startCh = puzzle.charAt(0);
            Set<Character> curSet = new HashSet<>();
            for (char c : puzzle.toCharArray()) {
                curSet.add(c);
            }
            for (Set<Character> set : index2WordSet) {
                if (set.contains(startCh) && curSet.containsAll(set)) {
                    count++;
                }
            }
            ans.add(count);
        }
        return ans;
    }


}
