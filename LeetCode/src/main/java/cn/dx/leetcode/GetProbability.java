package cn.dx.leetcode;

import org.apache.commons.lang.enums.Enum;

import java.util.Arrays;

/**
 * GetProbability 5427. 两个盒子中球的颜色数相同的概率
 * TODO 未完成
 * https://leetcode-cn.com/contest/weekly-contest-191/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/
 *
 * @author dongxian
 * @version 1.0
 * 20-5-31 上午11:34
 **/
public class GetProbability {
    public int factorial(int number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    public double getProbability(int[] balls) {
        int count = Arrays.stream(balls).sum();
        int allSuit = factorial(count) /
                (factorial(count / 2) * factorial(count - count / 2));
        int n = count / 2;
        int colorTypeCount = balls.length;

        return 0;
    }
}
