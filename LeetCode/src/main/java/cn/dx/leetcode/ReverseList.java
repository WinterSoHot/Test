package cn.dx.leetcode;

/**
 * 反转一个单链表。
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/20
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        // 前面节点
        ListNode pre = null;
        // 当前节点
        ListNode cur = head;
        while (cur != null) {
            // 保存下一个当前节点
            ListNode tmp = cur.next;
            // 把当前节点指向前面
            cur.next = pre;
            // 当前节点边前面节点
            pre = cur;
            // 下一个节点变成当前节点
            cur = tmp;
        }
        // 最后
        return pre;
    }
}
