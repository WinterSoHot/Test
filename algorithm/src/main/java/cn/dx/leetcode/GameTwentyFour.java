package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * https://leetcode-cn.com/problems/24-game/
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/22
 */
public class GameTwentyFour {

    private static final double EPSLION = 1E-6;
    private static final Double TARGET = 24d;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums){
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    /**
     * 回溯解决
     *
     * 思想，4个数，先从四个数中选两个数出来，两个数进行四则运算，和原数组剩余元素构成三个数，继续选择。
     *
     * 4个选有序两个  12中情况 * 4则运算
     * 3 - 2  6 * 4
     * 3
     *
     * 12 * 4 * 6 * 4 *2 * 4 = 9216
     *
     * 输入数据固定，处理过程固定
     * 时间复杂度 O(1) ，由于递归调用顶多4层因此，空间复杂度O(1)
     *
     * @param list 四个数的数组
     * @return 是否能够解决
     */
    private boolean solve(List<Double> list) {
        // 结束条件
        int size = list.size();
        if (size == 0){
            return false;
        }
        if (size == 1){
            return Math.abs(list.get(0) - TARGET) < EPSLION;
        }

        // 其他情况
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i==j){
                    continue;
                }
                List<Double> list2 = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    if (k!=i && k!=j){
                        list2.add(list.get(k));
                    }
                }
                for (int o = 0; o < 4; o++) {
                    // i和j顺序对结果除了除法不影响
                    if (o < 2 && i > j){
                        continue;
                    }
                    if (o == ADD){
                        list2.add(list.get(i) + list.get(j));
                    }else if (o == SUBTRACT){
                        list2.add(list.get(i) - list.get(j));
                    }else if (o == MULTIPLY){
                        list2.add(list.get(i) * list.get(j));
                    }else if (o == DIVIDE){
                        // 判断分子为0
                        if (Math.abs(list.get(j)) < EPSLION){
                            continue;
                        }else {
                            list2.add(list.get(i) / list.get(j));
                        }
                    }
                    // 迭代继续
                    if (solve(list2)){
                        return true;
                    }
                    // 迭代失败，回溯，删除最后一个元素
                    list2.remove(list2.size() - 1);
                }
            }
        }
        return false;
    }
}
