package cn.dx.offer;

import cn.dx.bullcode.ListNode;

/**
 * 链表中倒数第k个结点
 * <p>
 * 题目描述
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/8
 */
public class FindKthToTail {
    public ListNode FindKthToTail(ListNode pHead, int k) {
        if (pHead == null) {
            return null;
        }
        ListNode curNode = pHead;
        ListNode quickNode = pHead;
        int i = 1;
        for (; i <= k; i++) {
            quickNode = quickNode.next;
            if (quickNode == null) {
                break;
            }
        }
        if (i < k) {
            return null;
        }
        while (quickNode != null) {
            quickNode = quickNode.next;
            curNode = curNode.next;
        }
        return curNode;
    }
}
