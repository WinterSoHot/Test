package cn.dx.bullcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
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
