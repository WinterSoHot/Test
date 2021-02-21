package cn.dx.leetcode.weekly.c229;

import java.io.IOException;
import java.util.Scanner;

/**
 * 2020 卷 2：垃圾分类
 * <p>
 * 最近很多城市都搞起了垃圾分类，已知有一个小区有 n 堆垃圾需要丢弃，这些垃圾都打包了，我们并不知道这是什么垃圾，要知道有些垃圾如果丢在一起是会影响环境的。这个小区一共只有两辆垃圾车，我们希望在不影响环境的情况下，每次让垃圾车载走最多的垃圾，但是因为两位司机师傅有矛盾，所以两辆车装的垃圾数目一定要相同，不然其中一位司机师傅就会不开心。
 * 我们为垃圾袋标了号，分别是 1-n，有 m 个约束，每个约束表示为“a b”，意思是第a堆垃圾与第b堆垃圾不能装在一辆垃圾车上。(每堆垃圾最多有两个约束条件)
 * 请问两辆垃圾车一次最多可以带走多少堆垃圾呢?
 * <p>
 * 输入描述:
 * 输入第一行包含两个正整数 n，m，表示共有 n 堆垃圾，m 个约束条件。(1<=n,m<=500)
 * 接下来有 m 行，每行有两个正整数，a，b，表示第 a 堆垃圾和第 b 堆垃圾不能放在一辆垃圾车上。(1<=a,b<=n)
 * <p>
 * 输出描述:
 * 输出仅包含一个正整数，表示两辆垃圾车一次最多带走的垃圾的数量。
 * 输入样例:
 * 5 2
 * 1 4
 * 3 4
 * 输出样例:
 * 4
 * <p>
 * 作者：滴滴
 * 链接：https://leetcode-cn.com/leetbook/read/didiglobal1/el2kn6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class GarbageClassification {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            uf.union(start, end);
        }
        int ret = n;
        for (int i = 0; i < n; i++) {
            if (uf.rank[i] > 1) {
                ret -= uf.rank[i] & 1;
            }
        }
        ret -= ret & 1;
        System.out.println(ret);
    }

    static class UnionFind {
        /**
         * 当前i的父节点
         */
        int[] parent;
        /**
         * 当前i的子树的高度
         */
        int[] rank;
        int count;

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
