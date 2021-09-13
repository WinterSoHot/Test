package cn.dx.leetcode;

import java.util.Stack;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/7
 */
public class IsSameTree {
    /**
     * 判断两棵树是否相同
     *
     * 想法：层次遍历树节点，通过Stack保存每棵树的节点，每次pop节点进行比较是否相同，不相同则返回false
     *
     * @param p 第一颗数
     * @param q 第二课数
     * @return 是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> pStack = new Stack<>();
        Stack<TreeNode> qStack = new Stack<>();
        if (p == null && q == null){
            return true;
        }
        pStack.push(p);
        qStack.push(q);
        while (pStack.size() > 0){
            TreeNode curP = pStack.pop();
            TreeNode curQ = qStack.pop();
            if (curP == null && curQ == null){
                continue;
            }
            if(curP == null){
                return false;
            }
            if(curQ == null){
                return false;
            }
            if (curP.val == curQ.val){
                pStack.push(curP.left);
                pStack.push(curP.right);
                qStack.push(curQ.left);
                qStack.push(curQ.right);
            }else{
                return false;
            }
        }
        return true;
    }
}
