package cn.dx.leetcode;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/15
 **/
public class IntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p != null ? p.next : headA;
            q = q != null ? q.next : headB;
        }
        return p;
    }
}
