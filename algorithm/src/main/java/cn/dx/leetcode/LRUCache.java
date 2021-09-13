package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 分析：
 * 需要在O(1)时间内根据key查找元素，哈希表
 * <p>
 * 需要保存最近使用的情况 链表 双向链表
 * <p>
 * 可以参考 {@link java.util.LinkedHashMap}
 */
class LRUCache {

    static class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = null, end = null;
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            setHead(node);
            return node.val;
        }
        return -1;
    }


    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            setHead(node);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
            }
            setHead(newNode);
            map.put(key, newNode);
        }
    }

    private void setHead(Node node) {
        node.pre = null;
        node.next = head;
        if (head != null) {
            head.pre = node;
        }
        head = node;
        if (end == null) {
            end = head;
        }
    }

    private void remove(Node node) {
        if (node.pre == null) {
            // 当前节点是头节点
            head = node.next;
        } else {
            node.pre.next = node.next;
        }
        if (node.next == null) {
            // 尾节点
            end = node.pre;
        } else {
            node.next.pre = node.pre;
        }
    }

    public static void main(String[] args) {
        LRUCache lc = new LRUCache(2);
        lc.put(1, 1);
        lc.put(2, 2);
        System.out.println(lc.get(1));
        lc.put(3, 3);
        System.out.println(lc.get(2));
        lc.put(4, 4);
    }
}