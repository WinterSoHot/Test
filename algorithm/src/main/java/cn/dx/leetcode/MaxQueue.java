package cn.dx.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 实现队列
 * 请参照课程内容，用循环数组或者链表实现一个队列，包括：
 * <p>
 * push(int n) - 在队尾插入新元素 n，无需返回
 * pop() - 队首出队
 * front() - 获取队首元素
 * size() - 获取队列大小
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/high-frequency-algorithm-exercise/xyph5t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class MaxQueue {

    Deque<Integer> q = new ArrayDeque<>();
    Node head = null, tail = null;

    public MaxQueue() {
        head = new Node();
        tail = head;
    }

    public int max_value() {
        return q.isEmpty() ? -1 : q.peekFirst();
    }

    public void push_back(int value) {
        tail.next = new Node(value);
        while (!q.isEmpty() && q.peekLast() <= value) {
            q.removeLast();
        }
        q.addLast(value);
        tail = tail.next;
    }

    public int pop_front() {
        if (q.isEmpty()) {
            return -1;
        }
        int ret = head.next.val;
        if (ret == q.peekFirst()) {
            q.removeFirst();
        }
        head = head.next;
        return ret;
    }


    static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}