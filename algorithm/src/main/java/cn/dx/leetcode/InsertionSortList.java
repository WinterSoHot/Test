package cn.dx.leetcode;

/**
 * 对链表进行插入排序。
 * <p>
 * <p>
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 *  
 * <p>
 * 插入排序算法：
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/20
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        // 特殊情况
        if (head == null) {
            return null;
        }
        // 构建结果节点，存在头节点
        ListNode point = new ListNode();
        while (head != null) {
            // 当前节点
            ListNode curNode = new ListNode(head.val);
            // 开始节点为空
            if (point.next == null) {
                point.next = curNode;
            } else {
                // 插入排序
                ListNode preNode = point;
                ListNode compareNode = point.next;
                while (compareNode != null) {
                    if (compareNode.val >= curNode.val) {
                        preNode.next = curNode;
                        curNode.next = compareNode;
                        break;
                    }
                    preNode = compareNode;
                    compareNode = compareNode.next;
                }
                if (compareNode == null) {
                    preNode.next = curNode;
                }

            }
            head = head.next;
        }
        return point.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        InsertionSortList isl = new InsertionSortList();
        isl.insertionSortList(head);
    }
}
