package cn.dx.leetcode;

/**
 * LC删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/19
 */
public class DeleteLinkedDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ret = head;
        ListNode node = head;
        while (head != null) {
            if (head.val != node.val) {
                node.next = head;
                node = node.next;
            }
            head = head.next;
        }
        if (node != null) {
            node.next = null;
        }
        return ret;
    }
}
