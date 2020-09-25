package cn.dx.leetcode;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * <p>
 * 难度： 中等
 * 方法： 递归/ 前序遍历
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/25
 */
public class BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        中序遍历 inorder = [9,3,15,20,7]
//        后序遍历 postorder = [9,15,7,20,3]
        return buildNode(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    /**
     * 构建当前节点
     *
     * @param inorder   中序遍历数组
     * @param postorder 后序遍历数组
     * @param inStart   当前节点在中序遍历开始位置
     * @param inEnd     当前节点在中序遍历结束位置
     * @param postStart 当前节点在后序遍历开始位置
     * @param postEnd   当前节点在后序遍历结束位置
     * @return 节点
     */
    private TreeNode buildNode(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        // 结束条件
        if (inEnd < inStart || postEnd < postStart) {
            return null;
        }

        System.out.println("**************");
        System.out.println("中序遍历：Start:" + inStart + " End:" + inEnd);
        System.out.println("后续遍历：Start:" + postStart + " End:" + postEnd);

        int parent = postorder[postEnd];
        TreeNode node = new TreeNode(parent);
        int inMid = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == parent) {
                inMid = i;
                break;
            }
        }

        //左子树在数组中的长度
        int leftLen = inMid - inStart;

        //根据左子树数组长度，分割中序后续数组用于构建左右子树的位置
        node.left = buildNode(inorder, postorder, inStart, inMid - 1, postStart, postStart + leftLen - 1);
        node.right = buildNode(inorder, postorder, inMid + 1, inEnd, postStart + leftLen, postEnd - 1);
        return node;
    }

    public static void main(String[] args) {
        BuildTree bt = new BuildTree();
        TreeNode tree = bt.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println(tree);
    }
}
