package cn.dx.leetcode;

/**
 * 例题：删除链表的倒数第 N 个节点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/20
 */
public class RemoveNthNodeFromEnd {
    /**
     * @param n    倒数节点
     * @param head 头节点
     * @return 结果
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = getListNodeLen(head);
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode curNode = dummyNode;
        for (int i = 0; i < len - n; i++) {
            curNode = curNode.next;
        }
        curNode.next = curNode.next.next;
        return dummyNode.next;
    }

    /**
     * 一次扫描
     *
     * @param head 头节点
     * @param n    倒数节点
     * @return 结果
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode startNode = dummyNode;
        ListNode endNode = dummyNode;
        for (int i = 0; i <= n; i++) {
            endNode = endNode.next;
        }
        while (endNode != null) {
            startNode = startNode.next;
            endNode = endNode.next;
        }
        startNode.next = startNode.next.next;
        return dummyNode.next;
    }

    private int getListNodeLen(ListNode head) {
        int ret = 0;
        ListNode node = head;
        while (node != null) {
            ret++;
            node = node.next;
        }
        return ret;
    }
}
