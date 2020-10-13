package cn.dx.leetcode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/13
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        // 当前节点
        ListNode cur = head;
        // 后一个节点
        ListNode next = head.next;

        // next 为空 则不需要交换
        if (next != null) {
            // 记住下一个要交换的开始位置
            ListNode nn = next.next;

            // 交换
            ListNode tmp = cur;
            cur = next;
            cur.next = tmp;
            next = tmp;

            // 迭代
            next.next = swapPairs(nn);
        }
        return cur;
    }
}
