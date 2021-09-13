package cn.dx.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * https://leetcode-cn.com/classic/problems/top-k-frequent-elements/description/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/7
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        List<Integer> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequent tkf = new TopKFrequent();
        System.out.println(tkf.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }
}
