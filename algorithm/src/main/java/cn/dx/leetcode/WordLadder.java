package cn.dx.leetcode;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/5
 */
public class WordLadder {
    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edges = new LinkedList<>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 构建虚拟图
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        int endId = wordId.get(endWord);

        // 广度优先搜索
        Queue<Integer> que = new LinkedList<>();
        que.offer(beginId);
        dis[beginId] = 0;
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                // 因为采用了虚拟节点，所以一条边会算两个距离
                return dis[endId] / 2 + 1;
            }
            for (int it : edges.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    private void addEdge(String word) {
        addWord(word);
        Integer pid = wordId.get(word);
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 增加链接得虚拟节点
            char tmp = chars[i];
            chars[i] = '*';
            String newWord = new String(chars);
            addWord(newWord);
            int tid = wordId.get(newWord);
            edges.get(pid).add(tid);
            edges.get(tid).add(pid);
            chars[i] = tmp;
        }
    }

    private void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edges.add(new ArrayList<>());
        }
    }
}
