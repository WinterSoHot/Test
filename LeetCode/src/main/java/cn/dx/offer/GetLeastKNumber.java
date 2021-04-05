package cn.dx.offer;

import java.util.ArrayList;

/**
 * 最小的K个数
 * <p>
 * 题目描述
 * 给定一个数组，找出其中最小的K个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * 如果K>数组的长度，那么返回一个空的数组
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/10
 */
public class GetLeastKNumber {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        int len = input.length;
        ArrayList<Integer> ans = new ArrayList<>();
        if (len < k) {
            return ans;
        }
        buildMinHeap(input);
        for (int i = 0; i < k; i++) {
            ans.add(input[0]);
            swap(input, 0, len - 1);
            len--;
            minHeapify(0, input, len);
        }
        return ans;
    }

    private void buildMinHeap(int[] input) {
        for (int i = input.length / 2; i >= 0; i--) {
            minHeapify(i, input, input.length);
        }
    }

    private void minHeapify(int i, int[] input, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, min = i;
        if (l < heapSize && input[l] < input[min]) {
            min = l;
        }
        if (r < heapSize && input[r] < input[min]) {
            min = r;
        }
        if (min != i) {
            swap(input, i, min);
            minHeapify(min, input, heapSize);
        }
    }

    private void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

}
