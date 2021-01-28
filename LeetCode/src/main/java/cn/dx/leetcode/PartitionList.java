package cn.dx.leetcode;

/**
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: medium
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/3
 **/
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode first, second;
        first = new ListNode();
        second = new ListNode();

        // 保存节点的空head节点
        ListNode copyFirst = first;
        ListNode copySecond = second;
        while (head != null) {
            int val = head.val;
            if (val < x) {
                first.next = head;
                first = first.next;
            } else {
                second.next = head;
                second = second.next;
            }
            head = head.next;
        }
        // 防止循环
        second.next = null;
        // 拼接起来
        first.next = copySecond.next;
        // 返回结果
        return copyFirst.next;
    }

    public static void main(String[] args) {
        PartitionList pl = new PartitionList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        ListNode res = pl.partition(head, 3);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
