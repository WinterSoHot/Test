package cn.dx.leetcode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        return buildBST(nums, 0, n - 1);
    }

    private TreeNode buildBST(int[] nums, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (end - start) / 2 + start;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, start, mid - 1);
        node.right = buildBST(nums, mid + 1, end);
        return node;
    }
}
