package cn.dx.bullcode;

/**
 * 重建二叉树
 * <p>
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/1
 */
public class ReConstructBinaryTree {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return constructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode constructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 先序遍历，第一个为根节点
        int val = pre[preStart];
        TreeNode node = new TreeNode(val);
        // 找到父节点在中序遍历的位置，位置左边就是左子树的中序遍历，另一边就是右子树的
        int mid;
        for (mid = inStart; mid <= inEnd; mid++) {
            if (val == in[mid]) {
                break;
            }
        }
        // 没找到
        if (mid == inEnd + 1) {
            return node;
        }

        // 左子树的节点个数
        int leftWidth = mid - inStart;

        node.left = constructBinaryTree(pre, preStart + 1, preStart + leftWidth, in, inStart, mid - 1);
        node.right = constructBinaryTree(pre, preStart + leftWidth + 1, preEnd, in, mid + 1, inEnd);
        return node;
    }

    public static void main(String[] args) {
        ReConstructBinaryTree rcbt = new ReConstructBinaryTree();
        TreeNode tree = rcbt.reConstructBinaryTree(new int[]{1, 2, 4, 3, 5, 6}, new int[]{4, 2, 1, 5, 3, 6});
        System.out.println(tree);
    }
}
