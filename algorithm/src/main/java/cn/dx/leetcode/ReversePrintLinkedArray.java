package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * <p>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class ReversePrintLinkedArray {
    public int[] reversePrint(ListNode head) {
        List<Integer> ans = new ArrayList<>();
        printReverse(head, ans);
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }

    private void printReverse(ListNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        printReverse(node.next, ans);
        ans.add(node.val);
    }
}
