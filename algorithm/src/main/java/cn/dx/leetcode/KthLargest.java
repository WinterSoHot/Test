package cn.dx.leetcode;

import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 */
class KthLargest {
    private PriorityQueue<Integer> q = new PriorityQueue<>();
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            q.add(num);
        }
        for (int i = 0; i < nums.length - k; i++) {
            q.remove();
        }
    }

    public int add(int val) {
        q.add(val);
        for (int i = 0; i < q.size() - k; i++) {
            q.remove();
        }
        return q.element();
    }

    public static void main(String[] args) {
        KthLargest kl = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kl.add(3));
        System.out.println(kl.add(5));
        System.out.println(kl.add(10));
        System.out.println(kl.add(9));
        System.out.println(kl.add(4));
    }
}
