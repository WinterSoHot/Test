package cn.dx.leetcode;

/**
 * 先序遍历构造二叉树
 * <p>
 * 返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 * <p>
 * (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。此外，前序遍历首先显示节点 node 的值，然后遍历 node.left，接着遍历 node.right。）
 * <p>
 * 题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xu1q2h/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/23
 */
public class BSFFromPreOrder {
    public TreeNode bstFromPreorder(int[] preorder) {
        return buildNode(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildNode(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        int val = preorder[start];
        TreeNode node = new TreeNode(val);
        for (int i = start; i <= end; i++) {
            if (preorder[i] > val) {
                node.left = buildNode(preorder, start + 1, i - 1);
                node.right = buildNode(preorder, i, end);
                return node;
            }
        }
        node.left = buildNode(preorder, start + 1, end);
        return node;
    }
}
