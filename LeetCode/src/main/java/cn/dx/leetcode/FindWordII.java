package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 单词搜索 II
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xtkzjs/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 使用前缀树优化的深度优先搜索
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/31
 */
public class FindWordII {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        //建立前缀树
        TrieTree tree = new TrieTree(new TrieNode());
        for (String word : words) {
            tree.insert(word);
        }//插入字典中的单词
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                search(board, i, j, tree.root, ans);
            }
        }
        return ans;
    }

    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};

    /**
     * 深度优先搜索
     *
     * @param board 网格
     * @param x     当前x位置
     * @param y     当前y位置
     * @param root  当前对应的前缀树的节点
     * @param ans   答案列表
     */
    private void search(char[][] board, int x, int y, TrieNode root, List<String> ans) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 0 || root == null) {
            return;
        }
        if (root.isString) {
            // 如果当前节点是字典，添加到答案
            if (!ans.contains(root.s)) {
                ans.add(root.s);
            }
        }
        if (root.sub.containsKey(board[x][y])) {
            // 四周搜索
            for (int i = 0; i < 4; i++) {
                // 回溯
                char now = board[x][y];
                // 标记当前位置是否被使用
                board[x][y] = 0;
                search(board, x + dx[i], y + dy[i], root.sub.get(now), ans);
                // 撤销
                board[x][y] = now;
            }
        }
    }

    class TrieNode {
        String s;
        boolean isString;
        HashMap<Character, TrieNode> sub = new HashMap<>();

        public TrieNode() {
            isString = false;
            s = "";
        }
    }

    class TrieTree {
        TrieNode root;

        public TrieTree(TrieNode node) {
            root = node;
        }

        public void insert(String s) {
            TrieNode now = root;
            for (int i = 0; i < s.length(); i++) {
                if (!now.sub.containsKey(s.charAt(i))) {//不存在改单词就建立新节点插入该单词
                    now.sub.put(s.charAt(i), new TrieNode());
                }
                now = now.sub.get(s.charAt(i));
            }
            now.s = s;
            now.isString = true;
        }

        public boolean find(String s) {
            TrieNode now = root;
            for (int i = 0; i < s.length(); i++) {
                if (!now.sub.containsKey(s.charAt(i))) {
                    return false;
                }
                now = now.sub.get(s.charAt(i));
            }
            return now.isString;
        }
    }
}
