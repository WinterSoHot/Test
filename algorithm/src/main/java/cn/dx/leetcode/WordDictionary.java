package cn.dx.leetcode;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *  
 * <p>
 * 作者：图灵教育
 * 链接：https://leetcode-cn.com/leetbook/read/programmation-efficace/91r0dr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/30
 */
public class WordDictionary {

    boolean isEnd = false;
    WordDictionary[] next = new WordDictionary[26];

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {

    }

    public void addWord(String word) {
        WordDictionary node = this;
        char[] s = word.toCharArray();
        for (char ch : s) {
            if (node.next[ch - 'a'] == null) {
                node.next[ch - 'a'] = new WordDictionary();
            }
            node = node.next[ch - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return deepFirstSearch(word, this);
    }

    /**
     * 由于 通配符的 存在需要深度优先搜索达到模糊搜索
     *
     * @param word
     * @param node
     * @return
     */
    public boolean deepFirstSearch(String word, WordDictionary node) {
        char[] s = word.toCharArray();
        for (int i = 0; i < s.length; i++) {
            char ch = s[i];
            if (ch == '.') {
                for (int j = 0; j < 26; j++) {
                    if (node.next[j] != null && deepFirstSearch(word.substring(i + 1), node.next[j])) {
                        return true;
                    }
                }
                return false;
            }
            if (node.next[ch - 'a'] == null) {
                return false;
            }
            node = node.next[ch - 'a'];
        }
        return node.isEnd;
    }
}
