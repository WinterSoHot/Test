package cn.dx.offer;

import cn.dx.bullcode.ListNode;

/**
 * 合并两个排序的链表
 * <p>
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/7
 */
public class MergeList {
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
            }
        }
        if (list1 != null) {
            while (list1 != null) {
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
            }
        }
        if (list2 != null) {
            while (list2 != null) {
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
