package cn.dx.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/9
 */
public class LinkedLikeCycle {
    /**
     * Set记录
     *
     * 空间 O(n)
     *
     * @param head 头节点
     * @return 是否有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode node = head.next;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }

    /**
     * 追及法
     * 走路快的总能追上走路慢的
     *
     * 空间 O(1)
     *
     * @param head 头节点
     * @return 是否有环
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode node1 = head;
        ListNode node2 = head;

        while (true) {
            node1 = generateNode(node1, 1);
            node2 = generateNode(node2, 2);
            if (node1 == null || node2 == null) {
                break;
            }
            if (node1.equals(node2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 当前节点向前走step步
     *
     * @param node 当前节点
     * @param step 步数
     * @return 节点
     */
    public ListNode generateNode(ListNode node, int step) {
        for (int i = 0; i < step; i++) {
            if (node == null) {
                return null;
            }
            node = node.next;
        }
        return node;
    }
}
