package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 难度简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/24
 */
public class FindMode {

    Map<Integer, Integer> numFreq = new HashMap<>();

    public int[] findMode(TreeNode root) {
        DFS(root);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(numFreq.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        System.out.println(list);
        if (list.size() > 0) {
            return list.stream().filter(item -> item.getValue().equals(list.get(0).getValue())).mapToInt(Map.Entry::getKey).toArray();
        } else {
            return new int[]{};
        }
    }

    private void DFS(TreeNode root) {
        if (root == null) {
            return;
        }
        numFreq.put(root.val, numFreq.getOrDefault(root.val, 0) + 1);
        DFS(root.left);
        DFS(root.right);
    }
}
