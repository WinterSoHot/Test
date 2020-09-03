package cn.dx.bullcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 题目描述
 * 输入一个链表，反转链表后，输出新链表的表头
 *
 * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=188&&tqId=35160&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/3
 */
public class ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head = head.next;
        }
        Collections.reverse(list);
        ListNode node = new ListNode(list.get(0));
        ListNode res = node;
        for (int i = 1; i < list.size(); i++) {
            node.next = new ListNode(list.get(i));
            node = node.next;
        }
        return res;
    }
}
