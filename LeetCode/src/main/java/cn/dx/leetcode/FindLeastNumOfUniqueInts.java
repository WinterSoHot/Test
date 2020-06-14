package cn.dx.leetcode;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * FindLeastNumOfUniqueInts https://leetcode-cn.com/contest/weekly-contest-193/problems/least-number-of-unique-integers-after-k-removals/
 *
 * @author dongxian
 * @version 1.0
 * 20-6-14 上午10:53
 **/
public class FindLeastNumOfUniqueInts {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> num4Count = new HashMap<>();
        for (int i : arr) {
            num4Count.put(i, num4Count.getOrDefault(i, 0) + 1);
        }
        int numCount = num4Count.size();
        List<Map.Entry<Integer, Integer>> list = num4Count.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        }).collect(Collectors.toList());
        System.out.println(list);
        for (Map.Entry<Integer, Integer> entry : list) {
            if (entry.getValue() <= k) {
                k -= entry.getValue();
                numCount--;
            } else
                break;
        }
        return numCount;
    }
}
