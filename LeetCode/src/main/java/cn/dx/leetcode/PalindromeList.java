package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/23
 */
public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int front = 0;
        int back = list.size() - 1;
        while (front < back) {
            if (!list.get(front).equals(list.get(back))) {
                return false;
            }
            front++;
            back--;
        }

        return true;
    }
}
