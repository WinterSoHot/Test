package cn.dx.leetcode;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * <p>
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * <p>
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/accounts-merge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/18
 **/
public class AccountsMerge {
    /**
     * 方法：每个帐户作为节点，构建并查集 47/49 超时
     *
     * @param accounts 输入
     * @return 输出
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ret = new LinkedList<>();
        int n = accounts.size();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                List<String> item1 = accounts.get(i);
                List<String> item2 = accounts.get(j);
                boolean isConnected = false;
                for (int x = 1; x < item1.size(); x++) {
                    if (item2.contains(item1.get(x))) {
                        isConnected = true;
                        break;
                    }
                }
                if (isConnected) {
                    union(parent, i, j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (find(parent, i) == i) {
                // 当前是根节点
                Set<String> item = new HashSet<>(accounts.get(i));
                for (int j = 0; j < n; j++) {
                    if (find(parent, j) == i) {
                        List<String> other = accounts.get(j);
                        other.remove(0);
                        item.addAll(other);
                    }
                }
                ret.add(new LinkedList<>(item));
            }
        }
        for (List<String> item : ret) {
            item.sort((o1, o2) -> {
                if (o1.contains("@") && o2.contains("@")) {
                    return o1.compareTo(o2);
                } else if (!o1.contains("@")) {
                    return -1;
                } else {
                    return 1;
                }
            });
        }
        return ret;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (index != parent[index]) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    /**
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/accounts-merge/solution/zhang-hu-he-bing-by-leetcode-solution-3dyq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 对email作为节点建模，构建并查集
     */
    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, Integer> emailToIndex = new HashMap<>(6);
            Map<String, String> emailToName = new HashMap<>(6);
            int emailsCount = 0;
            for (List<String> account : accounts) {
                String name = account.get(0);
                int size = account.size();
                for (int i = 1; i < size; i++) {
                    String email = account.get(i);
                    if (!emailToIndex.containsKey(email)) {
                        emailToIndex.put(email, emailsCount++);
                        emailToName.put(email, name);
                    }
                }
            }
            UnionFind uf = new UnionFind(emailsCount);
            // 将邮箱连接合并
            for (List<String> account : accounts) {
                String firstEmail = account.get(1);
                int firstIndex = emailToIndex.get(firstEmail);
                int size = account.size();
                for (int i = 2; i < size; i++) {
                    String nextEmail = account.get(i);
                    int nextIndex = emailToIndex.get(nextEmail);
                    uf.union(firstIndex, nextIndex);
                }
            }
            // 将所有连接的邮箱保存到数组
            Map<Integer, List<String>> indexToEmails = new HashMap<>(6);
            for (String email : emailToIndex.keySet()) {
                int index = uf.find(emailToIndex.get(email));
                List<String> account = indexToEmails.getOrDefault(index, new ArrayList<>());
                account.add(email);
                indexToEmails.put(index, account);
            }
            // 将index
            List<List<String>> merged = new ArrayList<>();
            for (List<String> emails : indexToEmails.values()) {
                Collections.sort(emails);
                String name = emailToName.get(emails.get(0));
                List<String> account = new ArrayList<>();
                account.add(name);
                account.addAll(emails);
                merged.add(account);
            }
            return merged;
        }
    }

    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }


    public static void main(String[] args) {
        AccountsMerge am = new AccountsMerge();
        List<List<String>> accounts = new LinkedList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        System.out.println(am.accountsMerge(accounts));
    }
}
