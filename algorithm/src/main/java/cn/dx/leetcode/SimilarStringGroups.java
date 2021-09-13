package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 839. 相似字符串组
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 * <p>
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * <p>
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * <p>
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 * <p>
 * level: hard
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/31
 */
public class SimilarStringGroups {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        List<int[]> edges = new LinkedList<>();
        // 根据字符串构件图
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnect(strs[i], strs[j])) {
                    edges.add(new int[]{i, j});
                }
            }
        }
        // 并查集构建联通性
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.count;
    }

    /**
     * 判断字符串的相似性
     * <p>
     * 比较不一样的字符数目
     *
     * @param str  第一个字符串
     * @param str1 第二个字符串
     * @return 是否联通
     */
    private boolean isConnect(String str, String str1) {
        int len = str.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str1.charAt(i)) {
                num++;
            }
            if (num > 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SimilarStringGroups ssg = new SimilarStringGroups();
        int res = ssg.numSimilarGroups(new String[]{"tars", "rats", "arts", "star"});
        System.out.println(res);
    }

    class UnionFind {
        /**
         * 当前i的父节点
         */
        private int[] parent;
        /**
         * 当前i的子树的高度
         */
        private int[] rank;
        private int count;

        /**
         * 构造方法
         *
         * @param N 节点个数
         */
        public UnionFind(int N) {
            this.count = N;
            this.parent = new int[N];
            this.rank = new int[N];
            for (int i = 0; i < N; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int getCount() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        /**
         * 找到当前节点的根节点
         *
         * @param x 节点x
         * @return 根节点编号
         */
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * 将 p 和 q 归并到相同的分量中
         *
         * @param p 节点p
         * @param q 节点q
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            if (rank[rootP] == rank[rootQ]) {
                parent[rootP] = rootQ;
                rank[rootQ]++;
            } else if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
            }
            count--;
        }
    }

}
