package cn.dx.offer;

import java.util.Stack;

/**
 * 包含min函数的栈
 * <p>
 * 题目描述
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/8
 */
public class MinStack {
    Stack<Integer> o = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public void push(int node) {
        o.push(node);
        if (min.isEmpty() || node <= min.peek()) {
            min.push(node);
        }
    }

    public void pop() {
        int p = o.pop();
        if (min.peek() == p) {
            min.pop();
        }
    }

    public int top() {
        return o.peek();
    }

    public int min() {
        return min.peek();
    }
}
