package cn.dx.leetcode;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.*;

/**
 * MinSumOfLengths https://leetcode-cn.com/contest/biweekly-contest-28/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 *
 * @author dongxian
 * @version 1.0
 * 20-6-13 下午10:40
 **/
public class MinSumOfLengths {
    private Map<String, Integer> map;
    private Set<String> cache = new HashSet<>();

    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> fuck = new HashMap<>(); // 保存前缀和 <和，下标>
        fuck.put(0, -1);

        int curPreSum = 0; // 保存当前位置前缀和
        int mi = 0;
        int ans = 0x7fffffff;
        List<Integer> m_f = new ArrayList<>(); // 长度为arr.length ,当前位置前面满足target的子数组长度

        for (int i = 0; i < arr.length; i++) {
            curPreSum += arr[i]; // 计算当前前缀和，并保存
            fuck.put(curPreSum, i);

            if (fuck.containsKey(curPreSum - target)) {
                // 计算是否存在前缀和等于 curPreSum - target，存在这存在位置到当前位置和为target
                int l_i = fuck.get(curPreSum - target); // 起点位置
                int ttt = i - l_i; // 子数组长度
                if (mi == 0)
                    // 表示前面还没有满足条件子数组
                    mi = ttt;
                else
                    // 判断当前子数组长度和前面子数组长度那个更短
                    mi = Math.min(mi, ttt);
                m_f.add(mi);

                if (l_i != -1 && m_f.get(l_i) != 0)
                    // 当找到的l_i之前存在哦子数组，和当前的子数组，求和
                    ans = Math.min(ans, m_f.get(l_i) + ttt);

            } else {
                m_f.add(mi);
            }
        }
        if (ans == 0x7fffffff)
            return -1;
        return ans;

//        int sum = Arrays.stream(arr).sum();
//        map = new HashMap<>();
//        findValue(arr, sum, arr.length, target, 0, arr.length - 1);
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                int res = o1.getValue() - o2.getValue();
//                if (res != 0) {
//                    return res;
//                } else {
//                    return o1.getKey().charAt(0) - o2.getKey().charAt(0);
//                }
//            }
//        });
//        System.out.println(list);
//        int lastStart = -1;
//        int lastEnd = -1;
//        int resCount = 0;
//        int res = 0;
//        for (Map.Entry<String, Integer> integerEntry : list) {
//            if (resCount == 2) {
//                break;
//            }
//            String key = integerEntry.getKey();
//            int start = Integer.parseInt(key.split(" ")[0]);
//            int end = Integer.parseInt(key.split(" ")[1]);
//            if (lastStart == -1 && lastEnd == -1) {
//                lastStart = start;
//                lastEnd = end;
//                resCount++;
//                res += integerEntry.getValue();
//            } else {
//                if ((end >= lastStart && end <= lastEnd) || (start >= lastStart && start <= lastEnd)) {
//                    continue;
//                } else {
//                    resCount++;
//                    res += integerEntry.getValue();
//                }
//            }
//        }
//        if (resCount < 2)
//            return -1;
//        return res;
    }

    private void findValue(int[] arr, int sum, int length, int target, int start, int end) {
        String key = start + " " + end;
        if (cache.contains(key)) {
            return;
        }
        cache.add(key);
        if (sum == target) {
            System.out.println(start + " " + end);
            if (!map.containsKey(key)) {
                map.put(key, length);
            }
            return;
        }
        if (start <= end && target > 0) {
            findValue(arr, sum - arr[end], length - 1, target, start, end - 1);
            findValue(arr, sum - arr[start], length - 1, target, start + 1, end);
        }
    }

//    private int findSubArray(int[] arr, int target, int endPoint, int count) {
//        if (target == 0) {
//            return count;
//        }
//        if (endPoint <= 0) {
//            return -1;
//        }
//        for (int point = endPoint; point >= 0; point--) {
//            if (arr[point] == target) {
//                return findSubArray(arr, 0, point - 1, count + 1);
//            } else if (arr[point] < target) {
//                return findSubArray(arr, target - arr[point], point - 1, count + 1);
//            }
//        }
//        return -1;
//    }


    public static void main(String[] args) {
        MinSumOfLengths msl = new MinSumOfLengths();
        System.out.println(msl.minSumOfLengths(new int[]{75, 5, 28, 14, 15, 3, 20, 40, 22, 18, 43, 1, 4, 10, 18, 1, 3, 33, 2, 20, 8, 11, 5, 1, 4, 16, 37, 1}, 80));
    }
}
