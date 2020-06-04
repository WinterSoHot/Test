package cn.dx.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dongxian
 * @version 1.0
 * @className LargestNumber
 * @description TODO 未完成
 * @date 20-5-16 下午11:10
 **/
public class LargestNumber {
    public String largestNumber(int[] cost, int target) {
        Set<Integer> costSet = new HashSet<>();
        for (int i : cost) {
            costSet.add(i);
        }
        List<Integer> costList = costSet.stream().sorted().collect(Collectors.toList());
        int [] tmpList = new int[costList.size()];
        String res = "";
        for (int i = 0; i < costList.size(); i++) {
            Integer tmpCost = costList.get(i);
            if (tmpCost < target){
                tmpList[i] = target / tmpCost;
                target = target % tmpCost;
                int maxIndex = -1;
                for (int i1 = 0; i1 < cost.length; i1++) {
                    if (cost[i1] == tmpCost){
                        if (i1 > maxIndex){
                            maxIndex =i1;
                        }
                    }
                }
                for (int j = 0; j < tmpList[i]; j++) {
                    res =(maxIndex+1)+ res;
                }
            }
        }
        if (target == 0){
            return res;
        }else {
            return "0";
        }
//        for (int i = 0; i < tmpList.length; i++) {
//
//        }
//        DP(costList,target,tmpList);
    }

    public static void main(String[] args) {
        LargestNumber ln = new LargestNumber();
        int [] cost = {4,3,2,5,6,7,2,5,5};
        int target = 9;
        System.out.println(ln.largestNumber(cost,9));;
    }

    private void DP(List<Integer> costList, int target, int[] tmpList) {
        for (int i = 0; i < costList.size(); i++) {
            Integer tmpCost = costList.get(i);
            if (tmpCost < target){
                tmpList[i] += 1;
                break;
            }
        }
    }
}
