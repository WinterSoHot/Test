package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * <p>
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvuyv3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class KthSmallest {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> val = new LinkedList<>();
        searchTree(val, root, k);
        return val.get(k - 1);
    }

    private void searchTree(List<Integer> val, TreeNode node, int k) {
        if (val.size() == k || node == null) {
            return;
        }
        searchTree(val, node.left, k);
        val.add(node.val);
        searchTree(val, node.right, k);
    }

}
