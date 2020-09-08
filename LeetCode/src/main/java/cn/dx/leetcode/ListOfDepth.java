package cn.dx.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 * https://leetcode-cn.com/classic/problems/list-of-depth-lcci/description/
 *
 * 难度:中等
 *
 * 方法：广度优先搜素哦
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/8
 */
public class ListOfDepth {
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(tree);
        while (!q.isEmpty()){
            int sz = q.size();
            ListNode head = null;
            ListNode node = null;
            // 扩散
            for (int i = 0; i < sz; i++) {

                // 生成答案
                TreeNode par = q.poll();
                if (node==null){
                    node = new ListNode(par.val);
                    head = node;
                }else {
                    node.next = new ListNode(par.val);
                    node = node.next;
                }

                // 入队
                if (null!=par.left){
                    q.offer(par.left);
                }
                if (null!=par.right){
                    q.offer(par.right);
                }
            }
            ans.add(head);
        }
        ListNode[] ansArr = new ListNode[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }
}
