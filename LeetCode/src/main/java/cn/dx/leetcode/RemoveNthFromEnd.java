package cn.dx.leetcode;


/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <p>
 * 难度：中等
 * <p>
 * 遍历，找到第 L -n + 1 删除
 * 栈：压栈，然后出栈n次，栈顶删除
 * 一次遍历：两个指针，第一个指针先跑n，然后两个指针一起跑，第一个指针到结束，则第二个指针到要删除的位置
 * <p>
 * 对于链表，添加空节点，可以减少对头节点的操作
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/18
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 对于列表问题，考虑添加一个空结点，这样就不需要对头节点进行特殊处理
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummy.next;
    }
}
