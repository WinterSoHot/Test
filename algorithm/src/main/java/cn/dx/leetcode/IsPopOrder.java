package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈的压入、弹出序列
 * <p>
 * 题目描述
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/9
 */
public class IsPopOrder {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int len = pushA.length;
        if (len == 0) {
            return true;
        }
        Deque<Integer> q = new ArrayDeque<>();
        int s = 0;
        for (int i = 0; i < len; i++) {
            q.push(pushA[i]);
            while (s < len && !q.isEmpty() && popA[s] == q.peek()) {
                q.pop();
                s++;
            }
        }
        return s == len;
    }

    public static void main(String[] args) {
        IsPopOrder ipo = new IsPopOrder();
        System.out.println(ipo.IsPopOrder(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 3, 5, 2, 1}
        ));

        System.out.println(ipo.IsPopOrder(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 3, 5, 2, 1}
        ));


    }
}
