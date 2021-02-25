package cn.dx.leetcode;

/**
 * 练习：二叉查找树中第 K 小的元素 II
 * <p>
 * 给定一个二叉搜索树，我们希望找到其中第 k 小的元素。
 * <p>
 * 在这道题目中，除了原始的二叉搜索树 root 以外，你还会得到一个和其结构完全一致的二叉树 nodenum_root，树上节点的值代表以该节点为根的子树的节点数量。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/high-frequency-algorithm-exercise/5hhxs5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/25
 */
public class KthSmallestNodeInTree {

    public int kthSmallestII(TreeNode root, TreeNode nodeNumRoot, int k) {
        if (root == null) {
            return -1;
        }
        int left = 0;
        if (nodeNumRoot.left != null) {
            left = nodeNumRoot.left.val;
        }
        if (k == left + 1) {
            return root.val;
        } else if (left >= k) {
            return kthSmallestII(root.left, nodeNumRoot.left, k);
        } else {
            return kthSmallestII(root.right, nodeNumRoot.right, k - left - 1);
        }
    }
}
