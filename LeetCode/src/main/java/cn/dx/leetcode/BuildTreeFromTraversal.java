package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/1
 */
public class BuildTreeFromTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i],i);
        }
        return buildTree(preorder,0, preorder.length -1,inorder,0,inorder.length-1,inMap);
    }

    /**
     *
     * 构建二叉树
     *
     * @param preorder 前序遍历数组
     * @param preStart 前序数组开始位置
     * @param preEnd 前序数组结束位置
     * @param inorder 中序遍历数组
     * @param inStart 中序数组开始位置
     * @param inEnd 中序数组结束位置
     * @param inMap 中序数组 位置映射数据
     * @return 二叉树节点
     */
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                              int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd){
            return null;
        }
        // 当前前序数组开始位置为根节点
        TreeNode node = new TreeNode(preorder[preStart]);
        // 计算中序数组中当前根节点的位置
        int inRoot = inMap.get(node.val);
        // 计算 node左子树节点的偏移位置
        int numsLeft = inRoot - inStart;
        // 从preStart+1,preStart+numsLeft构建左子树，中序遍历为inStart到根节点左边inRoot-1
        node.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);
        // 构造右子树
        node.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);
        return node;
    }

}
