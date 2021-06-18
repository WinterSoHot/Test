package cn.dx.leetcode;

import java.util.*;

/**
 * CheckIfPrerequisite TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-30 下午11:08
 **/
public class CheckIfPrerequisite {
    public Boolean checkRequisite(Map<Integer, List<Integer>> preMap, int key, int req) {
        List<Integer> vs = preMap.getOrDefault(key, null);
        if (vs == null) {
            return false;
        }
        if (vs.contains(req)) {
            return true;
        } else {
            for (Integer v : vs) {
                if (checkRequisite(preMap, v, req)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        List<Set<Integer>> list = new ArrayList<>(n);


        Map<Integer, List<Integer>> preMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int key = prerequisite[0];
            int value = prerequisite[1];
            if (!preMap.containsKey(key)) {
                List<Integer> item = new ArrayList<>();
                item.add(value);
                preMap.put(key, item);
            } else {
                preMap.get(key).add(value);
                for (Map.Entry<Integer, List<Integer>> listEntry : preMap.entrySet()) {
                    if (listEntry.getValue().contains(key)) {
                        preMap.get(listEntry.getKey()).add(value);
                    }
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(checkRequisite(preMap, query[0], query[1]));
        }
        return res;
    }
}
