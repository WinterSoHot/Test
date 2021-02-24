package cn.dx.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 方法：优先队列，每次保存K个元素
 * <p>
 * 转换成两个有序列表合并，每次合并一次
 * <p>
 * 分治法，在二分范围内进行合并，整个列表进行分治合并
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/24
 */
public class MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                q.offer(list);
            }
        }
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while (!q.isEmpty()) {
            node.next = q.poll();
            node = node.next;
            if (node.next != null) {
                q.offer(node.next);
            }
        }
        node.next = null;
        return head.next;
    }
}
