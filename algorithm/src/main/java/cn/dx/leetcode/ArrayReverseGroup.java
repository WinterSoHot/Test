package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * K 个一组翻转链表
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xi8sr5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/18
 */
public class ArrayReverseGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        Deque<ListNode> q = new ArrayDeque<>();
        ListNode res = null;
        ListNode node = null;
        while (head != null) {
            q.push(head);
            head = head.next;
            if (q.size() == k) {
                while (!q.isEmpty()) {
                    if (res == null) {
                        res = q.pop();
                        node = res;
                    } else {
                        node.next = q.pop();
                        node = node.next;
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            if (res == null) {
                res = q.pollLast();
                node = res;
            } else {
                node.next = q.pollLast();
                node = node.next;
            }
        }
        if (node != null) {
            node.next = null;
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayReverseGroup arg = new ArrayReverseGroup();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = arg.reverseKGroup(head, 2);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

