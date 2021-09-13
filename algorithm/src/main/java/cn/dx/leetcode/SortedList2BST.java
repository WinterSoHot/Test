package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为<strong>高度平衡</strong>的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 分析：
 * 最先 思路 （1） 想太多
 * 首先列表中的数组为单调递增，由于二叉搜索树，左边节点小于父节点，右边节点大于根节点。
 * 使用列表中的数据构建二叉搜索树，只会产生一种情况导致不平衡，即往不平衡点的右子树的右孩子添加节点。
 * 因此需要单旋转
 *
 * 本题不要求模拟二叉排序树的插入过程，只需要最后构建成树满足平衡的二叉搜素树条件即可
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/18
 */
public class SortedList2BST {

//    /**
//     * 由于本题的特殊，只需要不停的往右子树搜索
//     *
//     * @param node
//     * @param tree
//     * @return
//     */
//    public TreeNode getParent(TreeNode node, TreeNode tree) {
//        while (tree != null && tree.right != node) {
//            tree = tree.right;
//        }
//        return tree;
//    }
//
//    public int getNodeHeight(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        return Math.max(getNodeHeight(node.right), getNodeHeight(node.left)) + 1;
//    }
//
//    public boolean isBalanced(TreeNode node) {
//        return Math.abs(getNodeHeight(node.left) - getNodeHeight(node.right)) <= 1;
//    }
//
//    /**
//     * 找最下面的不平衡点
//     * @param tree
//     * @return
//     */
//    public TreeNode findLoseBalance(TreeNode tree) {
//        List<TreeNode> unBalanceNodes = new LinkedList<>();
//        while (tree != null) {
//            if (!isBalanced(tree)){
//                unBalanceNodes.add(tree);
//            }
//            tree = tree.right;
//        }
//        if (unBalanceNodes.size() == 0){
//            return null;
//        }
//        return unBalanceNodes.get(unBalanceNodes.size()-1);
//    }
//
//    public TreeNode sortedListToBST(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//        TreeNode root = new TreeNode(head.val);
//        TreeNode pre = root;
//        while (head.next != null) {
//            pre.right = new TreeNode(head.next.val);
//            head = head.next;
//            pre = pre.right;
//            TreeNode loseBalance = findLoseBalance(root);
//            if (loseBalance != null) {
//                TreeNode parent = getParent(loseBalance, root);
//                if (parent == null) {
//                    root = loseBalance.right;
//                    loseBalance.right = root.left;
//                    root.left = loseBalance;
//                    continue;
//                }
//                parent.right = loseBalance.right;
//                loseBalance.right = parent.left;
//                parent.left = null;
//            }
//        }
//        return root;
//    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> data = new ArrayList<>();
        data.add(head.val);
        while (head.next != null) {
            data.add(head.next.val);
            head = head.next;
        }
        int num = data.size() - 1;
        TreeNode root = new TreeNode();
        binaryAllo(root, 0, num,data);
        return root;
    }

    private void binaryAllo(TreeNode root, int start, int end,List<Integer> data) {
        int mid = (end - start) / 2 + start;
        root.val = data.get(mid);
        if (mid > start){
            root.left = new TreeNode();
            binaryAllo(root.left,start,mid-1,data);
        }
        if (mid < end){
            root.right = new TreeNode();
            binaryAllo(root.right,mid+1,end,data);
        }
    }

    public static void main(String[] args) {
        int[] testData = new int[]{-10, -3, 0, 5, 9};
        ListNode testCast = new ListNode(-10);
        ListNode head = testCast;
        for (int i = 1; i < testData.length; i++) {
            int v = testData[i];
            testCast.next = new ListNode(v);
            testCast = testCast.next;
        }
        SortedList2BST sb = new SortedList2BST();
        sb.sortedListToBST(head);
    }
}
