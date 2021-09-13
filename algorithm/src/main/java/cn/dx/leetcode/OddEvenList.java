package cn.dx.leetcode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/13
 */
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        // 拆分两个链表
        ListNode oddHead = head;
        ListNode evenHead = head.next;

        // 记录当前两个链表的位置
        ListNode curOddNode = oddHead;
        ListNode curEvenNode = evenHead;

        while (curEvenNode != null) {
            if (curEvenNode.next == null) {
                break;
            }
            // 当前偶数节点下一个节点是奇数链表的下一个
            curOddNode.next = curEvenNode.next;
            curOddNode = curOddNode.next;

            // 当前奇数节点下一个节点是偶数链表的下一个
            curEvenNode.next = curOddNode.next;
            curEvenNode = curEvenNode.next;
        }
        // 在奇数链表后面添加偶数链表
        curOddNode.next = evenHead;
        return oddHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        OddEvenList oel = new OddEvenList();
        oel.oddEvenList(head);
    }
}
