package cn.dx.bullcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 *
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=188&&tqId=35156&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/6
 */
public class FirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        List<Integer> list = new ArrayList<>();
        while (pHead1!=null){
            list.add(pHead1.val);
            pHead1 = pHead1.next;
        }

        while (pHead2!=null){
            if (list.contains(pHead2.val)){
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }
}
