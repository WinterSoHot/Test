package cn.dx.leetcode;

import java.util.PriorityQueue;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/12/30
 */
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        // 最大堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            heap.add(stone);
        }
        while (heap.size() > 1) {
            int y = heap.poll();
            int x = heap.poll();
            if (y > x) {
                heap.offer(y - x);
            }
        }
        return heap.isEmpty() ? 0 : heap.poll();
    }

    public static void main(String[] args) {
        LastStoneWeight lsw = new LastStoneWeight();
        lsw.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1});
    }
}
