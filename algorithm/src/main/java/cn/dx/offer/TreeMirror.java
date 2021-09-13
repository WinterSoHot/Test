package cn.dx.offer;

import cn.dx.bullcode.TreeNode;

/**
 * 二叉树的镜像
 * <p>
 * 题目描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/7
 */
public class TreeMirror {
    public TreeNode Mirror(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode tmp = pRoot.right;
        pRoot.right = Mirror(pRoot.left);
        pRoot.left = Mirror(tmp);
        return pRoot;
    }
}
