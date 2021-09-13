package cn.dx.offer;

import cn.dx.bullcode.ListNode;

import java.util.ArrayList;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/4
 */
public class PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        add(listNode, ans);
        return ans;
    }

    private void add(ListNode listNode, ArrayList<Integer> ans) {
        if (listNode == null) {
            return;
        }
        add(listNode.next, ans);
        ans.add(listNode.val);
    }
}
