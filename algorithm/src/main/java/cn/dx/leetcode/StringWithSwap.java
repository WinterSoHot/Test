package cn.dx.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * <p>
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * <p>
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/11
 **/
public class StringWithSwap {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }
        char[] charArray = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            sb.append(hashMap.get(root).poll());
        }
        return sb.toString();
    }

    /**
     * 查找图中的动态连通性
     * 并查集（非基础的）
     */
    private class UnionFind {
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
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        /**
         * 将 p 和 q 归并到相同的分量中
         *
         * @param p 节点p
         * @param q 节点q
         */
        public void union(int p, int q) {
            //
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
