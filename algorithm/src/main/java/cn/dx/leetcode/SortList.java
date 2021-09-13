package cn.dx.leetcode;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/21
 */
public class SortList {
    /**
     * 递归
     * <p>
     * 其他方法：归并排序，自顶向下：快慢指针分两半，分别排序再合并，自底向上
     */
    public ListNode sortList(ListNode head) {
        return sort(head);
    }

    private ListNode sort(ListNode node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            return node;
        }
        ListNode next = sort(node.next);
        if (node.val < next.val) {
            node.next = next;
            return node;
        }
        ListNode retNode = next;
        while (next.next != null && next.next.val < node.val) {
            next = next.next;
        }
        node.next = next.next;
        next.next = node;
        return retNode;
    }
}
