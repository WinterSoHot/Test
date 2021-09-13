package cn.dx;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/14
 */
public class Main14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] inputs = new int[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = in.nextInt();
        }

        // 计算当前是否在AEB当中，大于等于4表示在AEB
        int matchAEB = 0;
        // 周期上报
        int timeRate = 0;
        // 交叉AEB
        int unionAEB = 0;
        List<Integer> res = new LinkedList<>();
        res.add(inputs[0]);
        int pre = inputs[0];

        for (int i = 1; i < n; i++) {
            if (pre - inputs[i] >= 9) {
                //检查速度变化
                matchAEB++;
            } else {
                // 是否是AEB的结束
                if (matchAEB >= 4) {
                    // 结束就将后两秒加入
                    for (int j = i; j < i + 4 && j < n; j++) {
                        res.add(inputs[j]);
                    }
                    // 并且2秒标记为可能交叉
                    unionAEB = 4;
                }
                // 充值AEB
                matchAEB = 0;
            }

            // 满足2秒，将之前的2秒加入
            if (matchAEB == 4) {
                for (int j = i - 3; j <= i; j++) {
                    // 检查是否重叠AEB
                    if (unionAEB > 0) {
                        unionAEB--;
                        continue;
                    }
                    res.add(inputs[j]);
                }
            }
            // 如果在AEB期间直接加入
            if (matchAEB > 4) {
                res.add(inputs[i]);
            }

            // 不在AEB进行周期计算
            if (matchAEB < 4) {
                timeRate++;
                // 达到周期
                if (timeRate == 60) {
                    res.add(inputs[i]);
                    timeRate = 0;
                }
            }
            // 检查是否重叠
            if (unionAEB > 0) {
                unionAEB--;
            }
            pre = inputs[i];
        }

        for (int i = 0; i < res.size(); i++) {
            if (i == res.size() - 1) {
                System.out.print(res.get(i));
                break;
            }
            System.out.print(res.get(i) + ",");
        }
    }
}
